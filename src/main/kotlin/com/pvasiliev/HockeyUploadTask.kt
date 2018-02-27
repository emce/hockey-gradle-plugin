package com.pvasiliev

import okhttp3.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class HockeyUploadTask : DefaultTask() {

    lateinit var apiToken: String
    lateinit var artifactPath: String
    private val okHttpClient = OkHttpClient()

    @TaskAction
    fun upload() {
        val fullPath = buildString {
            append(project.buildDir)
            append("\\")
            append(artifactPath)
        }
        val artifact = File(fullPath)
        val filePart = RequestBody.create(MediaType.parse("application/octet-stream"), artifact)
        val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("ipa", artifact.name, filePart)
                .build()
        val request = Request.Builder()
                .url("https://rink.hockeyapp.net/api/2/apps/upload")
                .header("X-HockeyAppToken", apiToken)
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