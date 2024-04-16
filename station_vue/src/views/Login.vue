<template>
  <div>
    <el-form :rules="rules" :model="loginForm" class="loginRoot">
      <h3 class="loginTitle">车站系统登陆</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" show-password/>
      </el-form-item>
      <el-button type="primary" style="width: 100%" @click="login">登陆</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password:
            [{required: true, message: '请输入密码', trigger: 'blur'}],
      },
    }
  },
  methods: {
    login() {
      this.$http.post("/users/login", {
        username: this.loginForm.username,
        password: this.loginForm.password
      }).then(response => {
        if (response.data.code === 200) {
          this.$message.success("登陆成功");
          localStorage.setItem("token", response.data.data.token);
          this.$router.push("/home");
        } else {
          this.$message.error("用户名或密码错误");
        }
      })
    }
  }
}
</script>

<style>
.loginRoot {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 50px auto;
  width: 350px;
  padding: 15px 35px;
  background: aliceblue;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #c6c6c6;
}

.loginTitle {
  margin: 15px auto 20px auto;
  text-align: center;
  color: #505050;
}
</style>