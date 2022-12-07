package com.gogolook.hw.app.enums

enum class LayoutTypeEnum(type:Int,typeString:String) {
    LIST_LAYOUT(0,"LIST"),
    GRID_LAYOUT(1,"GRID");

    companion object{
        fun getLayoutType(type:Int): LayoutTypeEnum {
            for (item in values()){
                if(item.ordinal == type){
                    return item
                }
            }
            return LIST_LAYOUT
        }
    }
}