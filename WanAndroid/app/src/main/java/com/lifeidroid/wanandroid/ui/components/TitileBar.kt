package com.lifeidroid.wanandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding
import com.lifeidroid.wanandroid.R

@Composable
fun TitileBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.bg_blue)
            )
            .fillMaxWidth()
            .height(75.dp)
            .padding(horizontal = 16.dp)
            .systemBarsPadding(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview(name = "TitileBar")
@Composable
private fun PreviewTitileBar() {
    TitileBar(Modifier) {
        Text(text = "标题")
    }
}