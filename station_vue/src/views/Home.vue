<template>
  <div>
    <el-container style="height: 100vh">
      <Aside></Aside>
      <el-container direction="vertical">
        <Header></Header>
        <el-main style="padding:0.1px">
          <div class="demo-image__lazy">
            <el-image v-for="url in urls" :key="url" :src="url" lazy scroll-container=".el-main"></el-image>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>

import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: "Home",
  components: {Header, Aside},
  data() {
    return {
      urls: [
        'https://www.toopic.cn/public/uploads/small/169224460733216922446076.jpg',
        'https://www.toopic.cn/public/uploads/small/1712590459629171259045920.png',
        'https://www.toopic.cn/public/uploads/small/1712590456673171259045699.jpg',
        'https://www.toopic.cn/public/uploads/small/1712590481268171259048152.jpg',
        'https://www.toopic.cn/public/uploads/small/171259051116417125905112.jpg',
        'https://www.toopic.cn/public/uploads/small/171259051997171259051994.jpg',
        'https://www.toopic.cn/public/uploads/small/1712590512489171259051233.png'
      ]
    }
  },
  created() {

  }
};

</script>
<style>


</style>