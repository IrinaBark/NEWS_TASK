<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="/styles/registrationForm.css">
<script type="text/javascript">
<%@include file="/script/validation.js"%>
</script>

<div class="wrapper">

	<div class="registration_form">
	
		<div class="title">Registration Form</div>


		<form name="form" action="controller" method="post"
			onsubmit="return validateRegistration()">
			<div class="form_wrap">
				<div class="input_grp">


					<div class="input_wrap">
						<label for="fname">First Name</label> <input type="text"
							id="firstName" name="firstName">
					</div>


					<div class="input_wrap">
						<label for="lastname">Last Name</label> <input type="text" id="lastName"
							name="lastName">
					</div>
				</div>


				<div class="input_wrap">
					<label for="email">Email Address</label> <input type="text"
						id="email" name="email">
				</div>

				<div class="input_wrap">
					<label for="login">Login</label> <input type="text" id="login"
						name="login">
				</div>
				<div class="input_wrap">
					<label for="password">Password</label> <input type="text"
						id="password" name="password">
				</div>
				<div class="input_wrap">
					<label for="repeatPassword">Repeat Password</label> <input
						type="text" id="repeatPassword" name="repeatPassword">
				</div>


				<div class="input_wrap">
					<input type="hidden" name="command" value="do_registration" /> <input
						type="submit" value="Register Now" class="submit_btn">
				</div>

			</div>
		</form>
	</div>
</div>
