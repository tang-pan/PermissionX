package com.yh.learningclan.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Created by yuhong on 2021/9/18
 * 任意类型指定一个别名
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    // 运行时权限申请结果的回调通知方式
    private var callback: PermissionCallback? = null

    fun requestNow(cb: (Boolean, List<String>) -> Unit, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    // 处理运行时权限的申请结果
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            // 记录所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            // 某个权限未被用户授权
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            // 申请的权限均已被授权
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}