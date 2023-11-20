<template>
  <HomePage>
    <div id="ttt" style="width: 1500px;height: 500px">
      Hello vue!
    </div>
  </HomePage>
</template>
<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>
import * as echarts from 'echarts';
// prettier-ignore
// Generate data
let category = [];
let dottedBase = +new Date();
let lineData = [];
let barData = [];
for (let i = 0; i < 20; i++) {
  let date = new Date((dottedBase += 3600 * 24 * 1000));
  category.push(
      [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-')
  );
  let b = Math.random() * 200;
  let d = Math.random() * 200;
  barData.push(b);
  lineData.push(d + b);
}
export default {
  components: {HomePage},
  data() {
    return {
      chart: null,
      option: Object,
      count: 11
    }
  },
  methods: {},
  created() {   /*配置项目设定*/
  }, mounted() {
    /*初始化Echarts*/
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('ttt'));
// 绘制图表
    // Enable data zoom when user click bar.
    const zoomSize = 6;

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
        data: category,
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
          data: lineData
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
          data: barData
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
          data: lineData
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
          data: lineData
        }
      ]
      /*The		End*/
    });
  },
}
</script>
<style scoped>
</style>
<style>
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

