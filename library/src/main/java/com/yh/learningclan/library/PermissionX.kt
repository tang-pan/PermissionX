package com.yh.learningclan.library

import androidx.fragment.app.FragmentActivity

/**
 * Created by yuhong on 2021/9/22
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    // FragmentActivity是AppCompatActivity的父类
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        // 获取FragmentManager的实例
        val fragmentManager = activity.supportFragmentManager
        // 判断传入的Activity参数中是否已经包含了指定TAG的Fragment
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            // 创建一个新的InvisibleFragment实例
            val invisibleFragment = InvisibleFragment()
            // 添加到Activity，同时指定一个TAG
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        // 申请运行时权限 *代表数组转换成可变长度参数
        fragment.requestNow(callback, *permissions)
    }

}