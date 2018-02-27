package com.pvasiliev

import org.gradle.api.Plugin
import org.gradle.api.Project

class HockeyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val hockeyExt = project.extensions.create("hockey", HockeyExtension::class.java)
        project.afterEvaluate {
            for ((variant, token) in hockeyExt.variantToApiToken) {
                project.tasks.create("upload${variant.capitalize()}", HockeyUploadTask::class.java, {
                    it.apiToken = token
                    it.artifactPath = hockeyExt.variantToOutputFile[variant]!!
                })
            }
        }
    }
}