pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
		maven("https://maven.minecraftforge.net/") { name = "MinecraftForge" }
		maven("https://repo.spongepowered.org/repository/maven-public/") { name = "Sponge" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		exclusiveContent {
			forRepository { maven("https://api.modrinth.com/maven") { name = "Modrinth" } }
			filter { includeGroup("maven.modrinth") }
		}
	}
	includeBuild("build-logic")
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
	id("dev.kikugie.stonecutter") version "0.9.2"
}

stonecutter {
	create(rootProject) {
		fun match(version: String, vararg loaders: String) {
			loaders.forEach { loader ->
				val buildscriptName = when {
					!version.startsWith("1.") && loader == "fabric" -> "build.fabric-m.gradle.kts"
					loader == "forge" && (version.startsWith("1.20.") || version.startsWith("1.21") || !version.startsWith("1.")) -> "build.forge21.gradle.kts"
					else -> "build.$loader.gradle.kts"
				}

				version("$version-$loader", version).buildscript = buildscriptName
			}
		}

		match("26.3", "fabric", "neoforge")
//		match("26.2", "fabric", "neoforge")
//		match("26.1", "fabric", "neoforge")

//		match("1.21.11", "fabric", "neoforge")

		vcsVersion = "26.3-fabric"
	}
}
