package com.wtmcodex.samplearticles.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.wtmcodex.samplearticles.R


object AppUtils {
    fun makePhoneCall(context: Context, phone: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL);
            intent.data = Uri.parse("tel:$phone");
            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.intent_choose_phone)
                )
            )
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "Error in your phone call " + e.localizedMessage,
                Toast.LENGTH_LONG
            ).show();
        }
    }

    fun sendEmail(context: Context, email: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, arrayListOf(email))
            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.intent_choose_email)
                )
            )

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "Error in sending email " + e.localizedMessage,
                Toast.LENGTH_LONG
            ).show();
        }
    }

    fun openWebLink(context: Context, url: String) {
        try {
            var link = url
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                link = "http://$url"

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link) // only email apps should handle this
            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.intent_choose_web_link)
                )
            )

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "Error in opening link " + e.localizedMessage,
                Toast.LENGTH_LONG
            ).show();
        }
    }

    fun openGoogleMap(context: Context, latitude: Double, longitude: Double, label: String) {
        try {
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$label")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "Error in opening Location " + e.localizedMessage,
                Toast.LENGTH_LONG
            ).show();
        }
    }
}