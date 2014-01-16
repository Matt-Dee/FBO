final baseUrl = "ftp://ftp.fbo.gov/"

def download(address)
{
    def file = new FileOutputStream(address.tokenize("/")[-1])
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

for(int i = 1; i < 16; i++){
   String day = ""

   if( ("" + i).length() == 1 ){
       day = "0" + i
   }else{
       day = i
   }
   downloadDay(baseUrl, "201401" + day)

}
//downloadDay(baseUrl, "20140102")