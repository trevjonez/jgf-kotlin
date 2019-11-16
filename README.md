# JGF-Kotlin

[ ![Download](https://api.bintray.com/packages/trevorjones141/maven/jgf-kotlin/images/download.svg) ](https://bintray.com/trevorjones141/maven/jgf-kotlin/_latestVersion)

A JsonGraphFormat implementation in kotlin for the JVM.

## Installation

Available via [jcenter](https://bintray.com/trevorjones141/maven/jgf-kotlin)

from jcenter: 
```groovy
implementation 'com.trevjonez.jgf:jgf-kotlin:$version'
```

## Usage

There are data classes provided that map to the JsonGraphFormat schema 

0. `Graph`
1. `Node`
2. `Edge`
3. `GraphsObject`

Additional helpers are provided for converting to and from Json

0. `val moshi = jsongraph.defaultMoshi()`
1. `val graphsAdapter = moshi.graphsAdapter()`

## License

    Copyright 2019 Trevor Jones

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.