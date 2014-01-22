final baseUrl = "ftp://ftp.fbo.gov/"

def download(address)
{
    new File("downloads").mkdir()
    def file = new FileOutputStream("./downloads/" + address.tokenize("/")[-1])
    def out = new BufferedOutputStream(file)
    out << new URL(address).openStream()
    out.close()
}

def downloadEverything(baseUrl){
    download(baseUrl + "datagov/FBOFullXML.xml")
}

def downloadDay(baseUrl, yyyyMMdd){
    download(baseUrl + "FBOFeed" + yyyyMMdd)
}

def printHelp(){
    println("Usage: ")
    println("    Yesterday:   groovy downloadfbo.groovy")
    println("    Single day:  groovy downloadfbo.groovy yyyyMMdd")
    println("    Date range:  groovy downloadfbo.groovy yyyyMMdd(begin) yyyyMMdd(end)")
}

if(args.length == 0){
    def today = new Date() - 1
    downloadDay(baseUrl, today.format("yyyyMMdd"))
}else if(args.length == 1){
    if(args[0].equalsIgnoreCase("-h")){
        printHelp()
    }else{
       downloadDay(baseUrl, args[0])
    }
}else if(args.length == 2){

    def beginDate = new Date().clearTime() 

    int y = Integer.parseInt(args[0].substring(0,4))
    int m = Integer.parseInt(args[0].substring(4,6))
    int d = Integer.parseInt(args[0].substring(6))

    beginDate.set(year: y, month: m - 1, date: d)

    def endDate = new Date().clearTime()

    int ey = Integer.parseInt(args[1].substring(0,4))
    int em = Integer.parseInt(args[1].substring(4,6))
    int ed = Integer.parseInt(args[1].substring(6))

    endDate.set(year: ey, month: em - 1, date: ed)

    currentDay = beginDate
    while( currentDay.compareTo(endDate) <= 0 ){
         println(currentDay.format("yyyyMMdd"))
         downloadDay(baseUrl, currentDay.format("yyyyMMdd"))
         currentDay = currentDay + 1
         println(currentDay.format("yyyyMMdd"))
    }
}
