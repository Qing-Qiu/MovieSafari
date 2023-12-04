<template>
  <a-card id="graph">
  </a-card>
</template>
<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>
import * as echarts from 'echarts';
import axios from "axios";
// for (let i = 0; i < 20; i++) {
//   let date = new Date((dottedBase += 3600 * 24 * 1000));
//   category.push(
//       [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-')
//   );
//   let b = 200;
//   let d = 200;
//   barData.push(b);
//   lineData.push(d + b);
// }
export default {
  data() {
    return {
      category: [],
      lineData: [],
      barData: [],
      movieName: [],
      chart: null,
      option: Object,
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
    }
  },
  created() {   /*配置项目设定*/
  },
  async mounted() {
    var myChart = echarts.init(document.getElementById('graph'));
    const zoomSize = 6;
    await this.fuck();
    console.log(this.category);
    console.log(this.barData);
    console.log(this.movieName);
    let movieName = this.movieName;
    myChart.setOption({
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
        // {
        //   name: 'line',
        //   type: 'line',
        //   smooth: true,
        //   showAllSymbol: true,
        //   symbol: 'emptyCircle',
        //   symbolSize: 15,
        //   data: this.lineData
        // },
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
        // {
        //   name: 'line',
        //   type: 'bar',
        //   barGap: '-100%',
        //   barWidth: 10,
        //   itemStyle: {
        //     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        //       {offset: 0, color: 'rgba(20,200,212,0.5)'},
        //       {offset: 0.2, color: 'rgba(20,200,212,0.2)'},
        //       {offset: 1, color: 'rgba(20,200,212,0)'}
        //     ])
        //   },
        //   z: -12,
        //   data: this.lineData
        // },
        // {
        //   name: 'dotted',
        //   type: 'pictorialBar',
        //   symbol: 'rect',
        //   itemStyle: {
        //     color: '#0f375f'
        //   },
        //   symbolRepeat: true,
        //   symbolSize: [12, 4],
        //   symbolMargin: 1,
        //   z: -10,
        //   data: this.lineData
        // }
      ],
      title: {
        text: '1911-2015年最受欢迎的电影及其人气',
        textStyle: {
          verticalAlign: 'bottom',
        }
      }
    });
  },
}
</script>
<style scoped>
</style>
<style>
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

