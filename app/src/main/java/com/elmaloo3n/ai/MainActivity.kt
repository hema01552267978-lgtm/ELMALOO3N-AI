package com.elmaloo3n.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF090909)
private val Panel = Color(0xFF171717)
private val Red = Color(0xFFE32636)
private val SoftWhite = Color(0xFFF3F3F3)

data class ChatMessage(val text: String, val fromUser: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Elmaloo3nApp() }
    }
}

@Composable
fun Elmaloo3nApp() {
    var input by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage("أهلاً بيك في ELMALOO3N AI 👑\nأنا مساعدك الذكي. اكتب سؤالك وهنبدأ.", false)
        )
    }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Ink,
        surface = Panel,
        primary = Red,
        onBackground = SoftWhite,
        onSurface = SoftWhite
    )) {
        Scaffold(
            containerColor = Ink,
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Panel).padding(horizontal = 18.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("👑", fontSize = 25.sp)
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text("ELMALOO3N AI", color = SoftWhite, fontWeight = FontWeight.Bold, fontSize = 19.sp)
                        Text("مساعدك الذكي", color = Red, fontSize = 12.sp)
                    }
                    TextButton(onClick = {
                        messages.clear()
                        messages.add(ChatMessage("محادثة جديدة. ابدأ سؤالك من هنا.", false))
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "محادثة جديدة", tint = Red)
                    }
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Panel).imePadding().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = input,
                        onValueChange = { input = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("اكتب رسالتك هنا...", color = Color.Gray) },
                        shape = RoundedCornerShape(24.dp),
                        maxLines = 5,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = SoftWhite,
                            unfocusedTextColor = SoftWhite,
                            focusedBorderColor = Red,
                            unfocusedBorderColor = Color.DarkGray,
                            cursorColor = Red
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            val text = input.trim()
                            if (text.isNotEmpty()) {
                                messages.add(ChatMessage(text, true))
                                messages.add(ChatMessage(
                                    "واجهة المحادثة جاهزة. الخطوة التالية هي ربط خادم الذكاء الاصطناعي لتوليد الردود الحقيقية.",
                                    false
                                ))
                                input = ""
                            }
                        },
                        modifier = Modifier.size(48.dp).background(Red, RoundedCornerShape(50))
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "إرسال", tint = Color.White)
                    }
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 14.dp),
                contentPadding = PaddingValues(vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { message ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (message.fromUser) Arrangement.End else Arrangement.Start
                    ) {
                        Surface(
                            color = if (message.fromUser) Color(0xFF3A171C) else Panel,
                            shape = RoundedCornerShape(18.dp),
                            modifier = Modifier.widthIn(max = 310.dp)
                        ) {
                            Text(
                                message.text,
                                modifier = Modifier.padding(14.dp),
                                color = SoftWhite,
                                fontSize = 16.sp,
                                lineHeight = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
