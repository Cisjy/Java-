<template>
  <div>
    <el-container style="height: 100vh">
      <Aside></Aside>
      <el-container direction="vertical">
        <Header></Header>
        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="id" label="ID" width="100">
            </el-table-column>
            <el-table-column prop="username" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="200">
            </el-table-column>
            <el-table-column prop="role" label="权限">
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>

import {defineComponent} from "vue";
import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";

export default ({
  name: "User",
  components: {Aside, Header},
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.$http.post("/users/getusers").then(res => {
      console.log(res);
      this.tableData = res.data.data;
    })
  }
})
</script>
<style scoped>

</style>