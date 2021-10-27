package com.grupo2.readybaggage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        private lateinit var locale: Locale
        private var currentLanguage = Locale.getDefault()
        private lateinit var currentLang: String
        private val mAwesomeValidationR = AwesomeValidation(ValidationStyle.BASIC)
        private val mAwesomeValidationL = AwesomeValidation(ValidationStyle.BASIC)
        private val mAwesomeValidationP = AwesomeValidation(ValidationStyle.BASIC)

        //Validaciones
        fun validationReg(activity: Activity): Boolean {

            mAwesomeValidationR.clear()
            mAwesomeValidationR.addValidation(activity, R.id.regViewTxtNombre, "[a-zA-Z\\s]+", R.string.nombre_error)
            mAwesomeValidationR.addValidation(activity, R.id.regViewTxtTelefono, "[0-9]{9,}$", R.string.telefono_error)
            mAwesomeValidationR.addValidation(activity, R.id.regViewTxtEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.email_error)
            mAwesomeValidationR.addValidation(activity, R.id.regViewTxtPass, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.pass_error)

            return mAwesomeValidationR.validate()
        }

        fun validationLog(activity: Activity): Boolean {


            mAwesomeValidationL.clear()
            mAwesomeValidationL.addValidation(activity, R.id.logViewTextEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.email_error)
            mAwesomeValidationL.addValidation(activity, R.id.logViewTextPass, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.pass_error)

            return mAwesomeValidationL.validate()
        }

        fun validationPro(activity: Activity): Boolean {

            mAwesomeValidationP.clear()
            mAwesomeValidationP.addValidation(activity, R.id.profileViewTextNombre, "[a-zA-Z\\s]+", R.string.nombre_error)
            mAwesomeValidationP.addValidation(activity, R.id.profileViewTextTelefono, "[0-9]{9,}$", R.string.telefono_error)
            mAwesomeValidationP.addValidation(activity, R.id.profileViewTextEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.email_error)
            mAwesomeValidationP.addValidation(activity, R.id.profileViewTextPass, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.pass_error)

            return mAwesomeValidationP.validate()
        }

        //Desplegar menú
        fun showPopup(v : View, activity: Activity, context: Context){
            val popup = PopupMenu(activity, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_idioma, popup.menu)
            //Controlar click en los items
            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.spanish-> {
                        Preferences.setUserPreferences(activity, "userLang","language","es")
                        setLocale("es", activity, context, null)
                    }
                    R.id.euskera-> {
                        Preferences.setUserPreferences(activity, "userLang","language","eu")
                        setLocale("eu", activity, context, null)
                    }
                    R.id.english-> {
                        Preferences.setUserPreferences(activity, "userLang","language","en")
                        setLocale("en", activity, context, null)
                    }
                }
                true
            }
            popup.show()
        }


        fun getCurrentDate(): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            return sdf.format(Date())
        }

        //cambiar idioma
        fun setLocale(localeName: String, activity: Activity, context: Context, avoidFirstRefresh: Boolean?) {
            locale = Locale(localeName)
            val res = activity.resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent (
                context,
                activity::class.java
            )
            currentLang=currentLanguage.toString()
            refresh.putExtra(currentLang, locale)
            if (avoidFirstRefresh == null) {
                activity.finish()
                context.startActivity(refresh)
            }
        }

        inline fun<reified activity: Activity> Activity.startActivity(){
            startActivity(Intent(this, activity::class.java))
        }

    }

}
