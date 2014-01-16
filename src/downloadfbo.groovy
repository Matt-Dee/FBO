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

downloadDay(baseUrl, "20140101")