package com.creatare.cvsccapp.flickr.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creatare.cvsccapp.R

@Composable
fun FlickrSearchField(
    searchTags: String,
    isVisible: Boolean = false,
    onValueChange: (String) -> Unit
) {
    if (isVisible) {
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(R.string.search_field_description))
            },
            singleLine = true,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = colorResource(R.color.silver),
                unfocusedContainerColor = colorResource(R.color.silver),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = searchTags,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = stringResource(R.string.search_field_placeholder), fontSize = 18.sp)
            }
        )
    }
}