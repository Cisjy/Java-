<template>
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="[] "
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#ffd04b"
             style="height: 100%"
             :router=true>

      <el-menu-item index="/home">
        <i class="el-icon-s-home"></i>
        <span slot="title">主页</span>
      </el-menu-item>
      <el-menu-item index="/user">
        <i class="el-icon-user"> </i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/station">
        <i class="el-icon-user"> </i>
        <span slot="title">车站管理</span>
      </el-menu-item>
      <el-menu-item index="/train">
        <i class="el-icon-user"> </i>
        <span slot="title">车次管理</span>
      </el-menu-item>
      <el-menu-item index="/order">
        <i class="el-icon-user"> </i>
        <span slot="title">订单管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>
<script>
export default {
  name: 'Aside'
}


</script>
<style>
.el-aside {
  color: #333;
}
</style>