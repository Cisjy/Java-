<template>
  <div>
    <el-container style="height: 100vh">
      <Aside></Aside>
      <el-container direction="vertical">
        <Header></Header>
        <el-main>
          <div style="margin-bottom: 5px;">
            <el-input v-model="name" placeholder="请输入车站名称" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost"></el-input>
            <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button type="success" @click="reset">重置</el-button>
            <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
          </div>
          <el-table :data="tableData" :stripe="true" size="medium">
            <!--            <el-table-column prop="id" label="ID" width="150">-->
            <!--            </el-table-column>-->
            <el-table-column label="id">
              <template slot-scope="scope">
                {{ (scope.$index + 1) + (pageNum - 1) * pageSize }}
              </template>
            </el-table-column>
            <el-table-column prop="name" label="车站">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
              <template slot-scope="scope">
                <el-popconfirm
                    title="确定删除吗？"
                    @confirm="del(scope.row.id)"
                    style="margin-left: 5px;"
                >
                  <el-button slot="reference" size="small" type="danger">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20,30]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </el-container>
    </el-container>
    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="车站名称" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
    </span>
    </el-dialog>
  </div>
</template>
<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: "Station",
  components: {
    Aside,
    Header
  },
  data() {
    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: "",
      centerDialogVisible: false,
      form: {
        id: "",
        name: "",
      },
      rules: {
        name: [
          {required: true, message: '请输入名字', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.loadPost();
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadPost();
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(id) {
      console.log(id);
      this.$http.get("/stations/delete?id=" + id).then(response => {
        if (response.data.code === 200) {
          this.$message.success("删除成功！");
          this.loadPost();
        } else {
          this.$message.error(response.data.message);
        }

      })
    },
    doSave() {
      this.$http.post("/stations/add", {name: this.form.name}).then(response => {
        console.log(response);
        if (response.data.code === 200) {
          this.$message.success("新增成功！");
          this.centerDialogVisible = false;
          this.loadPost();
          this.resetForm();
        } else {
          this.$message.error(response.data.message);
        }
      })
    },

    //加载页面分页
    loadPost() {
      this.$http.post("stations/listpage",
          {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {name: this.name}
          }).then(response => {
        console.log(response);
        if (response.data.code === 200) {
          this.tableData = response.data.data.list;
          this.total = response.data.data.total;
        } else {
          alert("获取数据失败！")
        }
      })
    },
    reset() {
      this.name = "";
      this.loadPost();
    },
    add() {
      this.centerDialogVisible = true
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log("新增");
          this.doSave();
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    }
  }
}
</script>
<style scoped>

</style>