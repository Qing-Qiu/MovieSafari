<template>
  <a-list :loading="loading">
    <div id="graph">
    </div>
  </a-list>
</template>
<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>
import axios from "axios";

export default {
  data() {
    return {
      category: [],
      lineData: [],
      barData: [],
      movieName: [],
      chart: null,
      option: Object,
      loading: true,
    }
  },
  methods: {
    async fuck() {
      try {
        const response = await axios.post('http://localhost:8080/chart/chart1',
            {}).then(response => {
          let len = response.data.length;
          for (let i = 0; i < len; i++) {
            this.category.push(response.data[i].year);
            this.barData.push(parseInt(response.data[i].popular));
            this.movieName.push(response.data[i].name);
            // this.lineData.push(parseInt(response.data[i].popular));
          }
        }, error => {
        })
      } catch (error) {
      }
    },
    async getGraph() {
      const echarts = await import('echarts');
      var mychart = echarts.init(document.getElementById('graph'));
      const zoomSize = 6;
      await this.fuck();
      this.loading = false;
      console.log(this.category);
      console.log(this.barData);
      console.log(this.movieName);
      let movieName = this.movieName;
      mychart.setOption({
        backgroundColor: '#fff',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (params) {
            let tooltip = params[0].name + '年<br>';
            for (let i = 0; i < params.length; i++) {
              tooltip += params[i].marker + ' ' + params[i].seriesName
                  + ': ' + '&nbsp;&nbsp;<strong>' + params[i].value + '</strong>' + '<br>';
              tooltip += movieName[params[i].dataIndex] + '<br>';
            }
            return tooltip;
          },
        },
        legend: {
          data: ['人气值'],
          textStyle: {
            color: '#ccc'
          }
        },
        xAxis: {
          data: this.category,
          axisLine: {
            lineStyle: {
              color: '#333'
            }
          }
        },
        yAxis: {
          splitLine: {show: false},
          axisLine: {
            lineStyle: {
              color: '#333'
            }
          }
        },
        series: [
          {
            name: '人气值',
            type: 'bar',
            barWidth: 10,
            itemStyle: {
              borderRadius: 5,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#14c8d4'},
                {offset: 1, color: '#43eec6'}
              ])
            },
            data: this.barData,
          },
        ],
        title: {
          text: '1911-2015年最受欢迎的电影及其人气',
          textStyle: {
            verticalAlign: 'bottom',
          }
        }
      });
    }
  },
  created() {   /*配置项目设定*/
  },
  async mounted() {
    // document.getElementById('graph').style.display = 'none';
    this.loading = true;
    await this.getGraph();
    this.loading = false;
    // document.getElementById('graph').style.display = 'block';
  },
}
</script>
<style scoped>
#graph {
  width: 1200px;
  height: 500px;
  margin-left: auto;
  margin-right: auto;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>