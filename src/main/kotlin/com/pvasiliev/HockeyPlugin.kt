package com.pvasiliev

import org.gradle.api.Plugin
import org.gradle.api.Project

class HockeyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val artifacts = project.container(Artifact::class.java)
        val hockeyExt = project.extensions.add("hockey", artifacts)
        project.afterEvaluate {
            artifacts.forEach { artifact ->
                project.tasks.create("upload${artifact.name.capitalize()}", HockeyUploadTask::class.java, {
                    it.apiToken = artifact.apiToken
                    it.artifactPath = artifact.path
                })
            }
        }
    }
}