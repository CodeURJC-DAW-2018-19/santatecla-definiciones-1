var myChart = echarts.init(document.getElementById("diagram"));
var option = {
  color: ["#27A1EE", "#F05050", "#43C472"],
  tooltip: {},
  legend: {
    data: [
      "Respuestas sin corregir",
      "Respuestas incorrectas",
      "Respuestas correctas"
    ]
  },
  xAxis: {
    data: ["Tema 1", "Tema 2"]
  },
  yAxis: {},
  series: [
    {
      name: "Respuestas sin corregir",
      type: "bar",
      stack: "Tema",
      data: [2, 3]
    },
    {
      name: "Respuestas incorrectas",
      type: "bar",
      stack: "Tema",
      data: [2, 1]
    },
    {
      name: "Respuestas correctas",
      type: "bar",
      stack: "Tema",
      data: [2, 4]
    }
  ]
};
myChart.setOption(option);
