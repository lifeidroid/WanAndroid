package com.lifeidroid.wanandroid.ui.navi

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

/**
 * <pre>
 *     author : lifei
 *     e-mail : lifeidroid@gmail.com
 *     time   : 2022/10/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeComposeDirections {
    data class HomeComposeArgs(
        val email: String,
        val vip: Boolean,
        val model:EmailModel
    )

    @Parcelize
    data class EmailModel(
        val email: String,
        val pwd: String
    ) : Parcelable

    companion object {
        val route = "home?email={email},vip={vip},model={model"
        val gson = Gson()
        val argumentList: MutableList<NamedNavArgument>
            get() = mutableListOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = "empty"
                },
                navArgument("vip") {
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument("model") {
                    type = object : NavType<EmailModel>(false) {

                        override val name: String
                            get() = "emailmodel"

                        /**
                         * 从' bundle '中获取这个类型的值
                         *
                         * @param bundle 要从中获取值的Bundle
                         * @param key    bundle key
                         * @return value of this type
                         */
                        override fun get(bundle: Bundle, key: String): EmailModel? {
                            return bundle.getParcelable(key)
                        }

                        /**
                         * 从String中解析此类型的值.
                         *
                         * @param value 此类型值的字符串表示形式
                         * @return 由该NavType表示的类型的解析值
                         * @throws IllegalArgumentException 如果value不能解析为此类型
                         */
                        override fun parseValue(value: String): EmailModel {
                            return gson.fromJson(value, object : TypeToken<EmailModel>() {}.type)
                        }

                        /**
                         * 将此类型的值放入' bundle '中
                         *
                         * @param bundle 要放入价值的Bundle
                         * @param key    包的关键
                         * @param value  此类型的值
                         */
                        override fun put(bundle: Bundle, key: String, value: EmailModel) {
                            bundle.putParcelable(key, value)
                        }

                    }
                }
            )

        fun parseArguments(backStackEntry: NavBackStackEntry): HomeComposeArgs {
            return HomeComposeArgs(
                email = backStackEntry.arguments?.getString("email") ?: "",
                vip = backStackEntry.arguments?.getBoolean("vip") ?: false,
                model = backStackEntry.arguments?.getParcelable<EmailModel>("model")!!
            )
        }

        fun actionLoginComposeToHomeCompose(
            email: String,
            vip: Boolean,
            model: EmailModel
        ): String {
            return "home?email=${email},vip=${vip},model=${Uri.encode(gson.toJson(model))}"
        }
    }
}