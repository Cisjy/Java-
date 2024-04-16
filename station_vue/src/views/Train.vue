<template>
  <div>
    <el-container style="height: 100vh">
      <Aside></Aside>
      <el-container direction="vertical">
        <Header></Header>
        <el-main>
          <div style="margin-bottom: 5px;">
            <el-input v-model="trainNumber" placeholder="请输入车次" suffix-icon="el-icon-search" style="width: 200px;"
                      @keyup.enter.native="loadPost">
            </el-input>
            <el-input v-model="departureStation" placeholder="请输入出发站" suffix-icon="el-icon-search"
                      style="width: 200px;"
                      @keyup.enter.native="loadPost">
            </el-input>
            <el-input v-model="arrivalStation" placeholder="请输入到达站" suffix-icon="el-icon-search"
                      style="width: 200px;"
                      @keyup.enter.native="loadPost">
            </el-input>
            <el-button type="primary" style="margin-left: 5px;" @click="search">查询</el-button>
            <el-button type="success" @click="reset">重置</el-button>

            <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
          </div>
          <el-table :data="tableData" :stripe="true" size="medium">
            <el-table-column prop="trainNumber" label="车次" width="150">
            </el-table-column>
            <el-table-column prop="price" label="票价/元" width="150">
            </el-table-column>
            <el-table-column prop="departureStationId" label="出发站" width="150">
            </el-table-column>
            <el-table-column prop="arrivalStationId" label="到达站" width="150">
            </el-table-column>
            <el-table-column prop="departureTime" label="出发时间" width="200">
            </el-table-column>
            <el-table-column prop="arrivalTime" label="到达时间" width="200">
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
        <el-form-item label="车次" prop="trainNumber">
          <el-col :span="20">
            <el-input v-model="form.trainNumber"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="售价" prop="price">
          <el-col :span="20">
            <el-input v-model="form.price"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="出发站编号" prop="departureStationId">
          <el-col :span="20">
            <el-input v-model="form.departureStationId"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="到达站编号" prop="arrivalStationId">
          <el-col :span="20">
            <el-input v-model="form.arrivalStationId"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="出发时间" prop="departureTime">
          <el-col :span="20">
            <el-date-picker
                v-model="form.departureTime"
                type="datetime"
                placeholder="选择日期时间">
            </el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="到达时间" prop="">
          <el-col :span="20">
            <el-date-picker
                v-model="form.arrivalTime"
                type="datetime"
                placeholder="选择日期时间">
            </el-date-picker>
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
  name: "Train",
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
      trainNumber: "",
      departureStation: "",
      arrivalStation: "",
      centerDialogVisible: false,
      form: {
        id: "",
        trainNumber: "",
        price: "",
        departureStationId: "",
        arrivalStationId: "",
        departureTime: "",
        arrivalTime: "",
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
    doMod() {
      this.$http.post("/trains/update", this.form).then(response => {
        console.log(response);
        if (response.data.code === 200) {
          this.$message.success("修改成功！");
          this.centerDialogVisible = false;
          this.loadPost();
          this.resetForm();
        } else {
          this.$message.error("修改失败！");
        }
      })
    },
    doSave() {
      this.$http.post("/trains/add", {form: this.form}).then(response => {
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
      this.$http.get("/trains/delete?id=" + id).then(response => {
        if (response.data.code === 200) {
          this.$message.success("删除成功！");
          this.loadPost();
        } else {
          this.$message.error(response.data.message);
        }

      })
    },
    loadPost() {
      this.$http.post("trains/listpage",
          {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              trainNumber: this.trainNumber,
              departureStation: this.departureStation,
              arrivalStation: this.arrivalStation
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
      this.$http.post("trains/listpage",
          {
            pageSize: this.pageSize,
            pageNum: this.pageNum,
            param: {
              trainNumber: this.trainNumber,
              departureStation: this.departureStation,
              arrivalStation: this.arrivalStation
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
      this.trainNumber = "";
      this.departureStation = "";
      this.arrivalStation = "";
      this.pageNum = 1;
      this.loadPost();
    },
    add() {
      this.centerDialogVisible = true
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            console.log("修改");
            this.doMod();
          } else {
            console.log("新增");
            this.doSave();
          }
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