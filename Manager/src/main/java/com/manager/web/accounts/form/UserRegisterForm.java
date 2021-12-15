package com.manager.web.accounts.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.manager.common.form.group.FormCreate;
import com.manager.common.form.group.FormEdit;
import com.manager.common.type.Role;
import com.manager.common.utils.ValueUtils;
import com.manager.common.validators.CheckUsername;
import com.manager.common.validators.FieldMatch;
import com.manager.web.accounts.entity.UserInfo;
import com.vvt.field.Choise;
import com.vvt.field.FieldTypeEnum;
import com.vvt.field.InputField;

import lombok.Data;

@Data
@FieldMatch(matchField = "password", field ="rePassword", message = "The confirm password must match")
public class UserRegisterForm{
	@NotBlank()
	@CheckUsername(exist = false, message = "username is used")
	@InputField(type = FieldTypeEnum.text, required = true, label = "Tên tài khoản:")
	private String username;
	@Email
	@NotBlank
	@InputField(type = FieldTypeEnum.text, required = true, label = "Email:")
	private String email;
	@NotBlank(groups = FormEdit.class)
	private String currentPassword;
	@NotBlank(groups = FormCreate.class)
	@InputField(type = FieldTypeEnum.password,label = "Mật khẩu:")
	private String password;
	@NotBlank(groups = FormCreate.class)
	@InputField(type = FieldTypeEnum.password,label = "Mật khẩu xác nhận:")
	private String rePassword;
	public UserInfo toUserInfo(Role role) {
		UserInfo user = UserInfo.createNewUserInfo(username, ValueUtils.encodePassword(password));
		user.setEmail(email);
		user.setRole(role);
		return user;
	}
}
