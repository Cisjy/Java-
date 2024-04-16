<template>
  <el-header style="text-align: right; font-size: 12px">
    <div style="display: flex;line-height: 60px;">
      <div style="margin-top: 8px;">
        <i style="font-size: 20px;cursor: pointer;"></i>
      </div>
      <div style="flex: 1;text-align: center;font-size: 34px;">
        <span>欢迎来到车站管理系统</span>
      </div>
      <el-dropdown>
        <span>管理员</span><i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
          <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-header>
</template>
<script>
export default {
  name: 'Header',
  data() {
    return {
      name: ''
    }
  },
  methods: {
    toUser() {
      const currentPath = this.$route.path;
      if (currentPath === '/user') {
        this.$message.warning('当前页面为个人中心');
      } else {
        this.$router.push('/user');
      }


    },
    logout() {
      this.$message.success('退出成功');
      localStorage.removeItem('token');
      this.$router.push('/login');
    }
  }

}
</script>
<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
</style>