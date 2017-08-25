#latte
1.core是最底层库   ec依赖core  app依赖ec
2.ec是电商库android库
3.annotations compiler是java库
4.app依赖ec annotations和 compiler库, 注意改为annotationProcessor project(':latte-compiler')

在core中建立一个初始化,在appliction中就可以使用,其余的所有模块都能使用
