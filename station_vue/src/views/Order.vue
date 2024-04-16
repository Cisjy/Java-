<template>
  <div>
    <el-container style="height: 100vh">
      <Aside></Aside>
      <el-container direction="vertical">
        <Header></Header>
        <el-main>
          <div style="margin-bottom: 5px;">
            <el-input v-model="username" placeholder="请输入姓名" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost">
            </el-input>
            <el-input v-model="trainNumber" placeholder="请输入车次" suffix-icon="el-icon-search"
                      style="width: 200px;"
                      @keyup.enter.native="loadPost">
            </el-input>
            <el-button type="primary" style="margin-left: 5px;" @click="search">查询</el-button>
            <el-button type="success" @click="reset">重置</el-button>
            <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
          </div>
          <el-table :data="tableData" :stripe="true" size="medium">
            <el-table-column prop="username" label="姓名" width="150">
            </el-table-column>
            <el-table-column prop="trainNumber" label="车次" width="150">
            </el-table-column>
            <el-table-column prop="createdAt" label="下单时间" width="200">
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
      <el-form ref="form" :rules="rules" :model="form" label-width="90px">
        <el-form-item label="姓名" prop="username">
          <el-col :span="20">
            <el-input v-model="form.username"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="车次" prop="trainNumber">
          <el-col :span="20">
            <el-input v-model="form.trainNumber"></el-input>
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
  name: "Order",
  components: {
    Aside,
    Header
  },
  data() {
    return {
      tableData: [],
      value1: '',
      pageSize: 10,
      pageNum: 1,
      total: 0,
      username: "",
      trainNumber: "",
      centerDialogVisible: false,
      form: {
        id: "",
        username: "",
        trainNumber: "",
        createdAt: "",
      },
      rules: {
        username: [
          {required: true, message: '请输入名字', trigger: 'blur'}
        ],
        trainNumber: [
          {required: true, message: '请输入车次', trigger: 'blur'}
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
    doSave() {
      console.log(this.form);
      this.$http.post("/orders/add", {
        id: this.form.id,
        username: this.form.username,
        trainNumber: this.form.trainNumber,
        createdAt: this.form.createdAt
      }).then(response => {
        console.log(response);
        if (response.data.code === 200) {
          this.$message.success("新增成功！");
          this.centerDialogVisible = false;
          this.loadPost();
          this.resetForm();
        } else {
          this.$message.error("新增失败！");
        }
      })
    },
    del(id) {
      console.log(id);
      this.$http.get("/orders/delete?id=" + id).then(response => {
        if (response.data.code === 200) {
          this.$message.success("删除成功！");
          this.loadPost();
        } else {
          this.$message.error(response.data.message);
        }

      })
    },
    loadPost() {
      this.$http.post("orders/listpage",
          {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              username: this.username,
              trainNumber: this.trainNumber,
            }
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
    search() {
      this.pageNum = 1;
      this.$http.post("orders/listpage",
          {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              username: this.username,
              trainNumber: this.trainNumber,
            }
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
      this.username = "";
      this.trainNumber = "";
      this.pageNum = 1;
      this.loadPost();
    },
    add() {
      this.centerDialogVisible = true
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.form.createdAt = new Date().toLocaleString();
          this.doSave();
        }
      })
    }
  }
}
</script>
<style scoped>

</style>