package com.foresightx.composesampleapp.feature.mine.impl

import android.R.attr.bottom
import android.R.attr.end
import android.R.attr.top
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import android.widget.ImageView
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import com.bumptech.glide.Glide
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

/**
 * 我的页路由入口。
 */
@Composable
fun MineRoute() {
    val vm: MineViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    MineScreen(uiState = uiState, onIntent = vm::onIntent)
}

/**
 * 我的页界面。
 *
 * @param uiState UI 状态。
 * @param onIntent 意图回调。
 */
@Composable
private fun MineScreen(
    uiState: MineUiState,
    onIntent: (MineUiIntent) -> Unit,
) {
    val pageBackground = Color(0xFFF7F7F7)
    val topBackgroundStart = Color(0xFFD7EEE8)
    val topBackgroundEnd = Color(0xFFE7F6F2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(topBackgroundStart, topBackgroundEnd),
                    ),
                ),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(72.dp))
                MineTopHeader(uiState = uiState)
                Spacer(modifier = Modifier.height(10.dp))
                MemberCard()
                EnergyCard()
                PetArchiveCard(avatarUrl = uiState.avatarUrl)
                ReportCard()
                MenuCard(
                    items = listOf(
                        "我的笔记",
                        "通知提醒",
                        "意见反馈",
                        "实名认证",
                    ),
                )
                ConstraintLayoutExampleCard()
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onIntent(MineUiIntent.SendSmsCode) },
                        enabled = !uiState.isSendingCode && uiState.phone.isNotBlank(),
                    ) {
                        Text(if (uiState.isSendingCode) "发送中" else "获取验证码")
                    }
                    Button(
                        onClick = { onIntent(MineUiIntent.SubmitLogin) },
                        enabled = !uiState.isLoading,
                    ) {
                        Text("登录")
                    }
                    Button(
                        onClick = { onIntent(MineUiIntent.Logout) },
                        enabled = !uiState.isLoading && uiState.isLoggedIn,
                    ) {
                        Text("退出")
                    }
                }
                uiState.statusMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color(0xFF15CCC0),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

/**
 * ConstraintLayout-Compose 示例。
 *
 * 说明：
 * 1. 这是纯 Compose 代码，不涉及 XML。
 * 2. 用于演示复杂相对约束：头像、标题、描述、按钮的互相约束关系。
 */
@Composable
private fun ConstraintLayoutExampleCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
        ) {
            val (avatar, title, desc, action) = createRefs()

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFCEEDEA))
                    .constrainAs(avatar) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )

            Text(
                text = "ConstraintLayout 示例",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF272829),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(avatar.end, margin = 10.dp)
                    top.linkTo(avatar.top)
                    end.linkTo(action.start, margin = 10.dp)
                    width = Dimension.fillToConstraints
                },
            )

            Text(
                text = "等价 XML 约束布局，但写在 .kt 里。",
                fontSize = 12.sp,
                color = Color(0xFF707173),
                modifier = Modifier.constrainAs(desc) {
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    top.linkTo(title.bottom, margin = 4.dp)
                    bottom.linkTo(avatar.bottom)
                    width = Dimension.fillToConstraints
                },
            )

            Button(
                onClick = {},
                modifier = Modifier.constrainAs(action) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            ) {
                Text("操作")
            }
        }
    }
}

@Composable
private fun MineTopHeader(uiState: MineUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE7E7E7)),
            ) {
                GlideNetworkImage(
                    url = uiState.avatarUrl,
                    placeholderRes = R.drawable.mine_avatar_placeholder_figma,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = uiState.nickName.ifBlank { "未登录用户" },
                        fontSize = 30.sp / 1.5f,
                        fontWeight = FontWeight.W900,
                        color = Color(0xFF242629),
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFFF3859)),
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Color(0xFFDCDDE0), RoundedCornerShape(4.dp))
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                ) {
                    Text("V1用户", fontSize = 12.sp, color = Color(0xFFA2A3A5))
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MineHeaderAction("客服")
            MineHeaderAction("设置")
        }
    }
}

@Composable
private fun MineHeaderAction(text: String) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .border(1.dp, Color(0xFF272829), CircleShape),
        )
        Text(text = text, fontSize = 10.sp, color = Color(0xFF272829))
    }
}

@Composable
private fun MemberCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF69DDCC), Color(0xFFBEEFD9)),
                    ),
                ),
        ) {
            Column(modifier = Modifier.padding(start = 12.dp, top = 8.dp)) {
                Text("V1·萌新铲屎官", fontWeight = FontWeight.Bold, color = Color(0xFF09534E))
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text("184/2000", fontSize = 10.sp, color = Color(0xFF0D7A73))
                    Text("升级还需180成长值", fontSize = 10.sp, color = Color(0xFF0D7A73))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White.copy(alpha = 0.45f)),
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF15CCC0)),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.CenterEnd)
                    .padding(end = 12.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFF0D1A9))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Text("开通会员", color = Color(0xFF5F3A10), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
private fun EnergyCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC5EADC)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(88.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                    Text("当前能量", fontSize = 12.sp, color = Color(0xFF18ACA3))
                    Text("9999999", fontSize = 34.sp / 1.5f, color = Color(0xFF18ACA3), fontWeight = FontWeight.Bold)
                }
            }
            QuickActionTile(
                modifier = Modifier.weight(1f),
                title = "福利商城",
                subTitle = "兑好物, 享惊喜",
                iconColor = Color(0xFFFFD18A),
                iconRes = R.drawable.mine_action_mall_icon,
            )
            QuickActionTile(
                modifier = Modifier.weight(1f),
                title = "赚取能量",
                subTitle = "做任务, 免费得",
                iconColor = Color(0xFF95D9FF),
                iconRes = R.drawable.mine_action_energy_icon,
            )
            QuickActionTile(
                modifier = Modifier.weight(1f),
                title = "邀请用户",
                subTitle = "邀新领能量",
                iconColor = Color(0xFFFFB19A),
                iconRes = R.drawable.mine_action_invite_icon,
            )
        }
    }
}

@Composable
private fun QuickActionTile(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    iconColor: Color,
    iconRes: Int,
) {
    Card(
        modifier = modifier.height(88.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.2f)),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(28.dp),
                    contentScale = ContentScale.Fit,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, fontSize = 13.sp, color = Color(0xFF272829), fontWeight = FontWeight.Medium)
            Text(subTitle, fontSize = 10.sp, color = Color(0xFFA2A3A5))
        }
    }
}

@Composable
private fun PetArchiveCard(avatarUrl: String?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9E9E9)),
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("萌宠档案", fontSize = 22.sp / 1.5f, color = Color(0xFF272829), fontWeight = FontWeight.Medium)
                Text("⊕ 添加萌宠", fontSize = 12.sp, color = Color(0xFFA2A3A5))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFD4C6B5)),
                ) {
                    GlideNetworkImage(
                        url = avatarUrl,
                        placeholderRes = R.drawable.mine_pet_placeholder_figma,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                    Column {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Riley", fontWeight = FontWeight.Bold, color = Color(0xFF242629), fontSize = 15.sp)
                            Text("♂", color = Color(0xFF15CCC0), fontSize = 12.sp)
                        }
                        Text("金毛 · 5个月 · 射手座", fontSize = 12.sp, color = Color(0xFF6C717A))
                    }
                }
                Text("›", fontSize = 20.sp, color = Color(0xFF272829))
            }
            Text("— ·", color = Color(0xFF15CCC0), fontSize = 14.sp, modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally))
        }
    }
}

@Composable
private fun ReportCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDEDED)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFCAE8F4)),
                    contentAlignment = androidx.compose.ui.Alignment.Center,
                ) {
                    Text("报", fontSize = 16.sp, color = Color(0xFF15CCC0), fontWeight = FontWeight.Bold)
                }
                Column {
                    Text("报告记录", fontSize = 22.sp / 1.5f, color = Color(0xFF272829), fontWeight = FontWeight.Medium)
                    Text("报告涵盖宠物识别、健康问诊与营养方案", fontSize = 12.sp, color = Color(0xFF707173))
                }
            }
            Text("›", fontSize = 20.sp, color = Color(0xFF272829))
        }
    }
}

@Composable
private fun GlideNetworkImage(
    url: String?,
    placeholderRes: Int,
    modifier: Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(placeholderRes)
            }
        },
        update = { imageView ->
            if (url.isNullOrBlank()) {
                imageView.setImageResource(placeholderRes)
            } else {
                Glide.with(imageView)
                    .load(url)
                    .placeholder(placeholderRes)
                    .error(placeholderRes)
                    .into(imageView)
            }
        },
    )
}

@Composable
private fun MenuCard(items: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            items.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    Text(item, fontSize = 14.sp, color = Color(0xFF272829))
                    Text("›", fontSize = 18.sp, color = Color(0xFFA2A3A5))
                }
                if (index != items.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE7E7E7)),
                    )
                }
            }
        }
    }
}

/**
 * 我的页预览（未登录态）。
 */
@Preview(showBackground = true, name = "Mine-LoggedOut")
@Composable
private fun MineScreenLoggedOutPreview() {
    MineScreen(
        uiState = MineUiState(
            phone = "13800138000",
            code = "123456",
            nickName = "未登录",
            userId = null,
            isLoggedIn = false,
            statusMessage = "请输入验证码后登录",
        ),
        onIntent = {},
    )
}

/**
 * 我的页预览（已登录态）。
 */
@Preview(showBackground = true, name = "Mine-LoggedIn")
@Composable
private fun MineScreenLoggedInPreview() {
    MineScreen(
        uiState = MineUiState(
            nickName = "示例用户",
            userId = 10001L,
            isLoggedIn = true,
            statusMessage = "已恢复本地登录状态",
        ),
        onIntent = {},
    )
}
