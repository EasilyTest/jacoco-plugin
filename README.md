JaCoCo Java Jenkins-plugin
=================================

[![Build Status](https://dev.azure.com/jacoco-org/JaCoCo/_apis/build/status/JaCoCo?branchName=master)](https://dev.azure.com/jacoco-org/JaCoCo/_build/latest?definitionId=1&branchName=master)
[![Build status](https://ci.appveyor.com/api/projects/status/g28egytv4tb898d7/branch/master?svg=true)](https://ci.appveyor.com/project/JaCoCo/jacoco/branch/master)
[![Maven Central](https://img.shields.io/maven-central/v/org.jacoco/jacoco.svg)](http://search.maven.org/#search|ga|1|g%3Aorg.jacoco)

基于jacoco-Jenkins的插件，修改了默认的 Analyzer 包括Analyzer,ClassAnalyzer,MethodAnalyzer,其中主要代码变更在MethodAnalyzer
通过git拉取代码进行增量比较，获取增量代码覆盖率。和原逻辑的全量覆盖率不冲突，不填写tag即为全量。
-------------------------------------------------------------------------
