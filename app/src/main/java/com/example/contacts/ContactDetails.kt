package com.example.contacts

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contacts.ui.theme.ContactsTheme

@Composable
fun ContactDetails(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContactAvatar(contact = contact)

        val firstLineName = if (contact.surname.isNullOrBlank()) {
            contact.name
        } else {
            "${contact.name} ${contact.surname}"
        }

        Text(
            text = firstLineName,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = Modifier.padding(top = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = contact.familyName,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 24.sp,
            )
            if (contact.isFavorite) {
                Image(
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = stringResource(id = R.string.favorite_contact),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        InfoRow(
            label = stringResource(id = R.string.phone),
            value = contact.phone,
        )
        InfoRow(
            label = stringResource(id = R.string.address),
            value = contact.address,
        )
        if (!contact.email.isNullOrBlank()) {
            InfoRow(
                label = stringResource(id = R.string.email),
                value = contact.email,
            )
        }
    }
}

@Composable
private fun ContactAvatar(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(top = 20.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (contact.imageRes != null) {
            Image(
                painter = painterResource(id = contact.imageRes),
                contentDescription = stringResource(
                    id = R.string.contact_photo,
                    "${contact.name} ${contact.familyName}",
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 108.dp, height = 72.dp),
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(68.dp),
            )
            Text(
                text = "${contact.name.take(1)}${contact.familyName.take(1)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.info_label_format, label),
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
        )
        Text(
            text = value,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
        )
    }
}

internal val sampleFavoriteContactWithoutPhoto = Contact(
    name = "Евгений",
    surname = "Андреевич",
    familyName = "Лукашин",
    imageRes = null,
    isFavorite = true,
    phone = "+7 495 495 95 95",
    address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
    email = "ELukashin@practicum.ru",
)

private val sampleContactWithPhoto = Contact(
    name = "Василий",
    surname = null,
    familyName = "Кузякин",
    imageRes = R.drawable.photo,
    isFavorite = false,
    phone = "---",
    address = "Ивановская область, дер. Крутово, д. 4",
    email = null,
)

@Preview(name = "portrait", showSystemUi = true)
@Composable
private fun ProfileWithoutPhotoPreview() {
    ContactsTheme {
        ContactDetails(contact = sampleFavoriteContactWithoutPhoto)
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
private fun ProfileWithPhotoPreview() {
    ContactsTheme {
        ContactDetails(contact = sampleContactWithPhoto)
    }
}
