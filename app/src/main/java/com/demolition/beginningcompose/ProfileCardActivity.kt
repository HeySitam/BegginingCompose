package com.demolition.beginningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demolition.beginningcompose.models.UserProfile
import com.demolition.beginningcompose.ui.theme.MyTheme

val userList: List<UserProfile> = listOf(
    UserProfile(R.drawable.ayush_pic, "This is Ayush Mondal", "Ayush Mondal", "Active Now"),
    UserProfile(R.drawable.my_pic, "This is Sitam Sardar", "Sitam Sardar", "Not Active"),
    UserProfile(R.drawable.ayush_pic, "This is Ayush Mondal", "Ayush Mondal", "Active Now"),
    UserProfile(R.drawable.my_pic, "This is Sitam Sardar", "Sitam Sardar", "Not Active"),
    UserProfile(R.drawable.ayush_pic, "This is Ayush Mondal", "Ayush Mondal", "Active Now"),
    UserProfile(R.drawable.my_pic, "This is Sitam Sardar", "Sitam Sardar", "Not Active"),
    UserProfile(R.drawable.ayush_pic, "This is Ayush Mondal", "Ayush Mondal", "Active Now"),
    UserProfile(R.drawable.my_pic, "This is Sitam Sardar", "Sitam Sardar", "Not Active")
)

class ProfileCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(topBar = {
        AppBar()
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn {
                items(userList){ user ->
                    ProfileCard(userProfile = user)
            }
        }

    }
}
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home icon description",
                modifier = Modifier.padding(start = 10.dp),
            )
        },
        title = {
            Text(text = "Demo App", color = Color.Black)
        },
        backgroundColor = Color(0xff64b5f6)
    )
}

@Composable
fun ProfileCard(
    userProfile: UserProfile
) {
    val borderStrokeColor: Color =
        if (userProfile.status == "Active Now") Color.Green else Color.Red
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(userProfile.imgId, userProfile.imgDesc, borderStrokeColor)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(picId: Int, contentDesc: String, borderStrokeColor: Color) {
    Card(
        modifier = Modifier.wrapContentSize(),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = borderStrokeColor)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://avatars.githubusercontent.com/u/76570320?v=4")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = contentDesc,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
                .size(72.dp)
        )
//        Image(
//            painter = painterResource(id = picId),
//            contentDescription = contentDesc,
//            modifier = Modifier.size(80.dp),
//            contentScale = ContentScale.Crop
//        )
    }

}

@Composable
fun ProfileContent(name: String, status: String) {
    Column(modifier = Modifier.padding(start = 8.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
        Text(
            text = status, style = MaterialTheme.typography.body2,
            color = Color.Black,
            modifier = Modifier.alpha(0.6F),
        )
    }

}

@Preview
@Composable
fun PreviewLayout() {
    MyTheme {
        MainScreen()
    }
}