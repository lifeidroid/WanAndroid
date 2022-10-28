package com.lifeidroid.wanandroid.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SectorView(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val path = Path()
        path.addArc(
            oval = Rect(
                0f,
                0f,
                canvasWidth ,
                canvasHeight * 2
            ), 180f, 180f
        )
//        drawArc(color = Color.Green, startAngle = 180f, sweepAngle = 180f, useCenter = true)
        drawPath(
            path = path,
            color = Color.White
        )
    }
}
