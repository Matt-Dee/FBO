import com.pci.fbo.json.doc.DocToIndex
import com.pci.fbo.pojo.KeyValue
import groovy.io.FileType

/**
 * User: mdonnelly
 * Date: 1/16/14
 * Time: 9:08 AM
 */
def parse(filename, type){
    def file = new File(filename)
    DocToIndex doc = new DocToIndex()
    StringBuilder sb = new StringBuilder();

    Map<String, String> fields = new HashMap<String, String>()

    file.eachLine { line ->
  
        if(line.contains("/" + type)){
            doc.setFields(fields)
            def http = "http://localhost:9200/fbo/" + type.toString().toLowerCase() + "/"

            def proc = ["curl", "-X POST", http, '-d ' + doc.toString()].execute()

            def returnCode = proc.text

            if( returnCode.contains("error") ){
                print( "\t\t" + doc.toString() + "\n\t\t" )
            }
            println( returnCode )
            proc.waitFor()

//            println(doc.toString())
        }else if(line.contains(type)){
            doc = new DocToIndex()
            doc.setType(type.toString().toLowerCase())
        }

        KeyValue kv = convertField(line)

        if(kv != null){
            fields.put(kv.getKey(), kv.getValue())
        }
    }

}

def  KeyValue convertField(String line){

    KeyValue keyValue = new KeyValue()

    if(line.contains("DATE")){
        keyValue.setKey("date")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("YEAR")){
        keyValue.setKey("year")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("AGENCY")){
        keyValue.setKey("agency")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("OFFICE")){
        keyValue.setKey("office")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("LOCATION")){
        keyValue.setKey("location")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("ZIP")){
        keyValue.setKey("zip")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("CLASSCOD")){
        keyValue.setKey("classcod")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("OFFADD")){
        keyValue.setKey("offadd")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("SUBJECT")){
        keyValue.setKey("subject")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("SOLNBR")){
        keyValue.setKey("solnbr")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("RESPDATE")){
        keyValue.setKey("respdate")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("ARCHDATE")){
        keyValue.setKey("archdate")
    }else if(line.contains("CONTACT")){
        keyValue.setKey("contact")
        keyValue.setValue(parseLine(line))
        keyValue.setValue(parseLine(line))
    }else if(line.contains("DESC")){
        keyValue.setKey("desc")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("LINK")){
        keyValue.setKey("link")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("URL")){
        keyValue.setKey("url")
        keyValue.setValue(parseLine(line))
    }else if(line.contains("SETASIDE")){
        keyValue.setKey("setaside")
        keyValue.setValue(parseLine(line))
    }

    if(keyValue.getKey() != null && keyValue.getValue() != null && keyValue.getKey().length() > 0 && keyValue.getValue().length() > 0){
        return keyValue
    }

    return null
}

def parseLine(String line){

    String [] val = line.split(">")
    if(val.size() == 2){
        val[1]
    }else if(val.size() > 2){
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < val.size(); i++){
            sb.append(val[i])
        }
        URLEncoder.encode( sb.toString(), "UTF-8" ).toString()

    }else{
        ""
    }
}

def parsePreSolCombine(String feed){
   parse(feed, "PRESOL")
   parse(feed, "COMBINE")
}


def list = []

def dir = new File("./downloads/")
dir.eachFileRecurse (FileType.FILES) { file ->
  list << file
}

list.each {
   parsePreSolCombine(it.path)
}

