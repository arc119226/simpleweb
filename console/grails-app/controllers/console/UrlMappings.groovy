package console

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        //rewrite url
        "/api/v$apiVersion/$apiName"(controller:"api", action:"fastapi")

        //report page
        "/api/index"(controller:"FastApi", action:"index")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
