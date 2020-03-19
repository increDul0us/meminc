package io.vertx.meme;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainVerticle extends AbstractVerticle {

    private FreeMarkerTemplateEngine templateEngine;

    @Override
    // tag::start[]
    public void start(Promise<Void> promise) throws Exception {
        Future<Void> steps = startHttpServer();
        steps.setHandler(ar -> {
        if (ar.succeeded()) {
            promise.complete();
        } else {
            promise.fail(ar.cause().getMessage());
        }
        });
    }
    // end::start[]

    // tag::startHttpServer[]
    private Future<Void> startHttpServer() {
        Promise<Void> promise = Promise.promise();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/assets/*").handler(StaticHandler.create("assets"));
        router.get("/").handler(this::indexHandler);
        router.get("/memes").handler(this::getMemeHandler);
        router.get("/about").handler(this::aboutHandler);
        router.get("/create").handler(this::addMemeHandler);
        router.get("/create/:id").handler(this::addMemeHandler);
        router.post().handler(BodyHandler.create());
        // router.post("/add").handler(BodyHandler.create()
        // .setUploadsDirectory("file-uploads"));
        router.post("/api/miminc").handler(this::mimincHandler);
        router.post("/api/fetch").handler(this::fetchMemeHandler);

        templateEngine = FreeMarkerTemplateEngine.create(vertx);
        int port;
        if (System.getProperty("PORT").isEmpty() || System.getProperty("PORT") == null){
            server
            .requestHandler(router)
            .listen(8080, ar -> {
                if (ar.succeeded()) {
                System.out.println("HTTP server running on port "+8080);
                promise.complete();
                } else {
                System.out.println("Could not start a HTTP server: "+ ar.cause().getMessage());
                promise.fail(ar.cause().getMessage());
                }
            });
        }  else  server
            .requestHandler(router)
            .listen(Integer.parseInt(System.getProperty("PORT")), ar -> {
                if (ar.succeeded()) {
                System.out.println("HTTP server running on port "+Integer.parseInt(System.getProperty("PORT")));
                promise.complete();
                } else {
                System.out.println("Could not start a HTTP server: "+ ar.cause().getMessage());
                promise.fail(ar.cause().getMessage());
                }
            });

        return promise.future();
    }
    // end::startHttpServer[]

    // tag::randomGenerator[]
    public String randomGenerator() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return(generatedString);
    }
    // end::randomGenerator[]

    // tag::indexHandler[]
    private void indexHandler(RoutingContext context) {
        WebClient client = WebClient.create(vertx);
        client.getAbs("https://api.imgflip.com/get_memes").send(ar ->{
            if (ar.succeeded()) {
                // Obtain response
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                JsonObject data = body.getJsonObject("data");
                JsonArray memes = data.getJsonArray("memes");
                context.put("memes", memes.getList());
                context.put("title", "home");
                templateEngine.render(context.data(), "templates/index.ftl", result -> {
                  if (result.succeeded()) {
                    context.response().putHeader("Content-Type", "text/html");
                    context.response().end(result.result());
                  } else {
                    context.fail(result.cause());
                  }
                });
            } else {
                context.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(ar.cause().getMessage());
            }
        });
    }
    // end::indexHandler[]

    // tag::getMemeHandler[]
    private void getMemeHandler(RoutingContext context) {
        WebClient client = WebClient.create(vertx);
        client.getAbs("https://api.imgflip.com/get_memes").send(ar ->{
            if (ar.succeeded()) {
                // Obtain response
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                JsonObject data = body.getJsonObject("data");
                JsonArray memes = data.getJsonArray("memes");
        // client.getAbs("http://alpha-meme-maker.herokuapp.com/").send(ar ->{
        //     if (ar.succeeded()) {
        //         // Obtain response
        //         HttpResponse<Buffer> response = ar.result();
        //         JsonObject body = response.bodyAsJsonObject();
        //         JsonArray memes = body.getJsonArray("data");
                context.put("memes", memes.getList());
                context.put("title", "memes");
                templateEngine.render(context.data(), "templates/memes.ftl", result -> {
                  if (result.succeeded()) {
                    context.response().putHeader("Content-Type", "text/html");
                    context.response().end(result.result());
                  } else {
                    context.fail(result.cause());
                  }
                });
            } else {
                context.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(ar.cause().getMessage());
            }
        });
    }
    // end::getMemeHandler[]

    // tag::addMemeHandler[]
    private void addMemeHandler(RoutingContext context) {
        WebClient client = WebClient.create(vertx);
        client.getAbs("https://api.imgflip.com/get_memes").send(ar ->{
            if (ar.succeeded()) {
                // Obtain response
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                JsonObject data = body.getJsonObject("data");
                JsonArray memes = data.getJsonArray("memes");
                context.put("memes", memes.toString());
                context.put("title", "create");
                templateEngine.render(context.data(), "templates/add.ftl", result -> {
                  if (result.succeeded()) {
                    context.response().putHeader("Content-Type", "text/html");
                    context.response().end(result.result());
                  } else {
                    context.fail(result.cause());
                  }
                });
            } else {
                context.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(ar.cause().getMessage());
            }
        });
    }
    // end::addMemeHandler[]

    // tag::aboutHandler[]
    private void aboutHandler(RoutingContext context) {
        context.put("title", "about");
        templateEngine.render(context.data(), "templates/about.ftl", result -> {
          if (result.succeeded()) {
            context.response().putHeader("Content-Type", "text/html");
            context.response().end(result.result());
          } else {
            context.fail(result.cause());
          }
        });
    }
    // end::aboutHandler[]

    // tag::mimincHandler[]
    private void mimincHandler(RoutingContext context) {
        JsonObject params = context.getBodyAsJson();
        // params.put("username", "kiyobi578");
        // params.put("password", "_6j?Z#FDU#3XVyZ");
        String template_id = params.getString("template_id");
        String text0 = params.getString("text0");
        String text1 = params.getString("text1");
        // String font = params.getString("font");
        // System.out.println("https://api.imgflip.com/caption_image?username=kiyobi578&password=tested&template_id="+template_id+"&text0="+text0+"&text1="+text1);
        WebClient client = WebClient.create(vertx);
        client.postAbs("https://api.imgflip.com/caption_image?username=kiyobi578&password=tested&template_id="+template_id+"&text0= "+text0+"&text1= "+text1)
        .send(ar ->{
            if (ar.succeeded()) {
                // Obtain response
                HttpResponse<Buffer> response = ar.result();
                try {
                    String body = response.body().toString();
                    JsonObject data = new JsonObject(body).getJsonObject("data");
                    // String url = data.getString("url");
                    // System.out.println(url);
                    // String page_url = data.getString("page_url");
                    context.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json")
                        .end(data.encodePrettily());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(ar.cause().getMessage());
            }
        });
    }
    // end::mimincHandler[]

    // tag::fetchMemeHandler[]
    private void fetchMemeHandler(RoutingContext context) {
        WebClient client = WebClient.create(vertx);
        client.getAbs("https://api.imgflip.com/get_memes").send(ar ->{
            if (ar.succeeded()) {
                // Obtain response
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                context.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(body.encodePrettily());
            } else {
                context.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(ar.cause().getMessage());
            }
        });
    }
    // end::fetchMemeHandler[]
}
