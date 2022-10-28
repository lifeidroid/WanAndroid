package com.lifeidroid.wanandroid.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeidroid.wanandroid.model.entity.net.SystemEntity
import com.lifeidroid.wanandroid.ui.components.TitileBar
import com.lifeidroid.wanandroid.ui.navigation.direction.SystemDetailDirections
import com.lifeidroid.wanandroid.viewmodel.SystemViewModel

@Composable
fun SystemPage(
    toSystemDetail:(args: SystemDetailDirections.SystemDetailArgs)->Unit,
    articleDetail: (String) -> Unit,
    vm: SystemViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true, block = {
        vm.getSystemTree()
    })

    Column {
        TitileBar(Modifier) {
            TabRow(
                divider = { 0.dp },
                backgroundColor = Color.Transparent,
                selectedTabIndex = vm.selectedIndex,
                contentColor = Color.White,
                indicator = { },
                modifier = Modifier.width(100.dp)
            ) {
                Tab(selected = vm.selectedIndex == 0, onClick = { vm.updateSelectedIndex(0) }) {
                    Text(text = "体系")
                }
                Tab(selected = vm.selectedIndex == 1, onClick = { vm.updateSelectedIndex(1) }) {
                    Text(text = "导航")
                }
            }
        }

        if (vm.selectedIndex == 0) {
            SystemForm(toSystemDetail = toSystemDetail, vm = vm)
        } else {
            NavigationForm(vm = vm, articleDetail = articleDetail)
        }
    }

}
