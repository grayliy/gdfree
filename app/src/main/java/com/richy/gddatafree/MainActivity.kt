package com.richy.gddatafree

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //获取访问权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            val permission_sms = arrayOf(Manifest.permission.SEND_SMS)
            requestPermissions(permission_sms,1)
        }

        //获取刚刚创建的两个控件
        val textShow = findViewById<TextView>(R.id.text)
        val button = findViewById<Button>(R.id.btnSendMessage)
        button.setOnClickListener{
            thread {
                val  result = sendMessage()
                runOnUiThread {
                    //这里进行ui更新操作
                    textShow.text=result
                }
            }
        }
        //
    }
    //发送短信的方法
    fun sendMessage(): String {
        var sms = SmsManager.getDefault()
        sms.sendTextMessage("10001",null,"42124",null,null)
        Thread.sleep(5000)
        sms.sendTextMessage("10001",null,"Y",null,null)
        return "短信发送完毕,祝您生活愉快,接收到确认成功短信后,就可以免费上网啦~"


    }
}