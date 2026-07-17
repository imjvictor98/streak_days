package com.cvj.app.streakdays.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cvj.app.streakdays.core.designsystem.theme.BeVietnamPro
import com.cvj.app.streakdays.core.designsystem.theme.StreakDaysTheme

@Composable
fun StreakPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (RowScope.() -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = 52.dp)
            .shadow(
                elevation = 16.dp, // Approximation of figma shadow
                shape = CircleShape,
                ambientColor = com.cvj.app.streakdays.core.designsystem.theme.PrimaryVariant,
                spotColor = com.cvj.app.streakdays.core.designsystem.theme.PrimaryVariant
            ),
        enabled = enabled,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = com.cvj.app.streakdays.core.designsystem.theme.PrimaryVariant,
            contentColor = com.cvj.app.streakdays.core.designsystem.theme.OnPrimary
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        if (leadingIcon != null) {
            leadingIcon()
        }
        Text(
            text = text,
            fontFamily = BeVietnamPro,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.01.em
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StreakPrimaryButtonPreview() {
    StreakDaysTheme {
        StreakPrimaryButton(
            text = "Create Goal",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
