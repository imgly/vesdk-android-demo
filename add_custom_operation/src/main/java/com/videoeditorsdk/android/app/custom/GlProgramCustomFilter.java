package com.videoeditorsdk.android.app.custom;

import androidx.annotation.WorkerThread;

import ly.img.android.pesdk.annotations.gl.GlProgramCreate;

@WorkerThread
@GlProgramCreate(
    create = "GlProgramBase_CustomFilter",
    vertexShader = "vertex_shader_hatch.glsl",
    fragmentShader = "fragment_shader_hatch.glsl"
)
class GlProgramCustomFilter extends GlProgramBase_CustomFilter { }
