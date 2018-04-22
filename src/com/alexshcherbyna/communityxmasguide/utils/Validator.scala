package com.alexshcherbyna.communityxmasguide.utils


/**
 * Created by al on 20.07.15.
 */
object Validator {

/*
  def validateLoginFields(email: String, password:  String, context: FragmentActivity): Boolean = {
    if (email.length() == 0) {
      Tools.showToast(context, R.string.enter_email);

      return   false
    }
    else{
      if(!isValidEmail(email)){
        Tools.showToast(context, R.string.incorrect_email);
        return   false
      }
    }

    if ((password.length() >= 0) && (password.length() < 6)) {
      Tools.showToast(context, R.string.enter_password);
      return  false
    }

    return  true
  }


  def validatePersonalRegistration( ur : UserRegistration, confirmPassword: String, context: Context) : Boolean = {


    if (ur.username.length() == 0) {
      Tools.showToast(context,
        R.string.enter_username);
      return false;
    }




    if (ur.email.length() == 0) {
      Tools.showToast(context, R.string.enter_email);

      return   false
    }
    else{
      if(!isValidEmail(ur.email)){
        Tools.showToast(context, R.string.incorrect_email);
        return   false
      }
    }



    if ((ur.password.length() >= 0) && (ur.password.length() < 6)) {
      Tools.showToast(context, R.string.enter_password);
      return  false
    }

    if (ur.password != confirmPassword) {
      Tools.showToast(context, R.string.passwords_does_not_match);
      return  false
    }

    true
  }

  def validateChangePassword(oldPassword: String,newPassword: String, confirmNewPassword: String, context: Context) : Boolean = {



    if (oldPassword.length() >= 0 && (oldPassword.length() < 6)) {
      Tools.showToast(context, R.string.enter_old_password);
      return  false
    }

    if (newPassword.length() >= 0 && (newPassword.length() < 6)) {
      Tools.showToast(context, R.string.enter_new_password);
      return  false
    }

    if (oldPassword.length() >= 0 && (oldPassword.length() < 6)) {
      Tools.showToast(context, R.string.enter_new_password_confirmation);
      return  false
    }

    if (newPassword != confirmNewPassword) {
      Tools.showToast(context, R.string.passwords_does_not_match);
      return  false
    }

    true
  }

  def validateBusinessRegistration(ur : UserRegistration, confirmPassword: String, context: Context) : Boolean = {
    if(!validatePersonalRegistration(ur , confirmPassword, context)){
      return  false
    }
    if(ur.phone.length() < 5) {
      Tools.showToast(context, R.string.enter_correct_phone_no);
      return false
    }

/*ur.phone match {
  case Some(phone) =>
    if (phone.length() < 5) {
      Tools.showToast(context, R.string.enter_correct_phone_no);
     return false
    }
    else{
     return true
    }

  case None => return false
}*/

    true
  }



 def isValidEmail(target: CharSequence ) : Boolean =  {
    if (TextUtils.isEmpty(target)) {
      return  false
    } else {
      android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
  }*/
}
