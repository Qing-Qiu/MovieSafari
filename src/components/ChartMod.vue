<template>
  <div id="graph">
  </div>
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
            this.lineData.push(parseInt(response.data[i].popular));
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
    myChart.setOption({
      backgroundColor: '#0f375f',
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['line', 'bar'],
        textStyle: {
          color: '#ccc'
        }
      },
      xAxis: {
        data: this.category,
        axisLine: {
          lineStyle: {
            color: '#ccc'
          }
        }
      },
      yAxis: {
        splitLine: {show: false},
        axisLine: {
          lineStyle: {
            color: '#ccc'
          }
        }
      },
      series: [
        {
          name: 'line',
          type: 'line',
          smooth: true,
          showAllSymbol: true,
          symbol: 'emptyCircle',
          symbolSize: 15,
          data: this.lineData
        },
        {
          name: 'bar',
          type: 'bar',
          barWidth: 10,
          itemStyle: {
            borderRadius: 5,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {offset: 0, color: '#14c8d4'},
              {offset: 1, color: '#43eec6'}
            ])
          },
          data: this.barData
        },
        {
          name: 'line',
          type: 'bar',
          barGap: '-100%',
          barWidth: 10,
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {offset: 0, color: 'rgba(20,200,212,0.5)'},
              {offset: 0.2, color: 'rgba(20,200,212,0.2)'},
              {offset: 1, color: 'rgba(20,200,212,0)'}
            ])
          },
          z: -12,
          data: this.lineData
        },
        {
          name: 'dotted',
          type: 'pictorialBar',
          symbol: 'rect',
          itemStyle: {
            color: '#0f375f'
          },
          symbolRepeat: true,
          symbolSize: [12, 4],
          symbolMargin: 1,
          z: -10,
          data: this.lineData
        }
      ]
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

