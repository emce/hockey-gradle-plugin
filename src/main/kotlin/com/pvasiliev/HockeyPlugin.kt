package com.pvasiliev

import okhttp3.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class HockeyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val okHttpClient = OkHttpClient()
        val hockeyExt = project.extensions.create("hockey", HockeyExtension::class.java)
        project.afterEvaluate {
            for ((variant, token) in hockeyExt.variantToApiToken) {
                val task = project.tasks.create("upload${variant.capitalize()}")
                task.doLast {
                    val filePath = buildString {
                        append(project.buildDir)
                        append("\\")
                        append(hockeyExt.variantToOutputFile[variant])
                    }
                    val outputFile = File(filePath)
                    val filePart = RequestBody.create(MediaType.parse("application/octet-stream"), outputFile)
                    val body = MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("ipa", outputFile.name, filePart)
                            .build()
                    val request = Request.Builder()
                            .url("https://rink.hockeyapp.net/api/2/apps/upload")
                            .header("X-HockeyAppToken", token)
                            .post(body)
                            .build()
                    val response = okHttpClient.newCall(request).execute()
                    if (response.isSuccessful) {
                        println("Upload successfully")
                    } else {
                        println("Upload failed")
                        println(response.message())
                    }
                }
            }
        }
    }
}