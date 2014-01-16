package com.pci.fbo.json.doc
/**
 * User: mdonnelly
 * Date: 1/16/14
 * Time: 12:34 PM
 */
class DocToIndex {
    String type
    Map<String, String> fields

    String toString(){
        if(fields == null || fields.size() == 0) return ""

        StringBuilder sb = new StringBuilder()
        sb.append("{$type:{")
        sb.append("\"id\":" + "\"" + this.hashCode() + "\"," )
        for(String key : fields.keySet()){
            sb.append("\"" + key + "\"")
            sb.append(":")
            sb.append("\"" + fields.get(key) + "\"")
            sb.append(",")
        }

        sb.deleteCharAt(sb.length() - 1)

        sb.append("}}")

        sb.toString()
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        DocToIndex that = (DocToIndex) o

        if (fields != that.fields) return false
        if (type != that.type) return false

        return true
    }

    int hashCode() {
        int result
        result = (type != null ? type.hashCode() : 0)
        result = 31 * result + (fields != null ? fields.hashCode() : 0)
        return result
    }
}
