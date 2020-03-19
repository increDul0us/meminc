<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Meminc</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300i,400,700" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.bootstrap3.min.css" integrity="sha256-ze/OEYGcFbPRmvCnrSeKbRTtjG4vGLHXgOqsyLFTRjg=" crossorigin="anonymous" />
    <link rel="stylesheet" href="/assets/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="/assets/css/jquery-ui.css">
    <link rel="stylesheet" href="/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/assets/css/owl.theme.default.min.css">

    <link rel="stylesheet" href="/assets/css/lightgallery.min.css">

    <link rel="stylesheet" href="/assets/css/bootstrap-datepicker.css">

    <link rel="stylesheet" href="/assets/fonts/flaticon/font/flaticon.css">

    <link rel="stylesheet" href="/assets/css/swiper.css">

    <link rel="stylesheet" href="/assets/css/aos.css">

    <link rel="stylesheet" href="/assets/css/style.css">
    <link rel="shortcut icon" href="/assets/images/favicon.png" />

  </head>
  <body>

  <div class="site-wrap">

    <div class="site-mobile-menu">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>




    <header class="site-navbar py-3" role="banner">

      <div class="container-fluid">
        <div class="row align-items-center">

          <div class="col-6 col-xl-2" data-aos="fade-down">
            <h1 class="mb-0 navbar-translate">
                <a href="/" class="navbar-brand text-black h2 mb-0">
                    <img width="70px" src="/assets/images/favicon.png" alt="Image" class="img-fluid">Meminc
                </a>
            </h1>
          </div>
          <div class="col-10 col-md-8 d-none d-xl-block" data-aos="fade-down">
            <nav class="site-navigation position-relative text-right text-lg-center" role="navigation">

              <ul class="site-menu js-clone-nav mx-auto d-none d-lg-block">
                <#if title == "home">
                    <li class="active"><a href="/">Home</a></li>
                    <li><a href="/memes">memes</a></li>
                    <li><a href="/about">about</a></li>
                    <li><a href="/create">create</a></li>
                <#elseif title == "memes">
                    <li><a href="/">Home</a></li>
                    <li class="active"><a href="/memes">Memes</a></li>
                    <li><a href="/about">about</a></li>
                    <li><a href="/create">create</a></li>
                <#elseif title == "about">
                    <li><a href="/">Home</a></li>
                    <li><a href="/memes">Memes</a></li>
                    <li class="active"><a href="/about">About</a></li>
                    <li><a href="/create">create</a></li>
                <#elseif title == "create">
                    <li><a href="/">Home</a></li>
                    <li><a href="/memes">Memes</a></li>
                    <li><a href="/about">About</a></li>
                    <li class="active"><a href="/create">create</a></li>
                </#if>
              </ul>
            </nav>
          </div>

          <div class="col-6 col-xl-2 text-right" data-aos="fade-down">
            <div class="d-none d-xl-inline-block">
              <ul class="site-menu js-clone-nav ml-auto list-unstyled d-flex text-right mb-0" data-class="social">
                <li>
                  <a href="https://github.com/increDul0us" target="_blank" class="pl-0 pr-3"><span class="icon-github"></span></a>
                </li>
                <li>
                  <a href="https://twitter.com/incredulous___" target="_blank"  class="pl-3 pr-3"><span class="icon-twitter"></span></a>
                </li>
                <li>
                  <a href="https://instagram.com/incredulous___" target="_blank" class="pl-3 pr-3"><span class="icon-instagram"></span></a>
                </li>
                <li>
                  <a href="javascript:void" class="pl-3 pr-3"><span class="icon-youtube-play"></span></a>
                </li>
              </ul>
            </div>

            <div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle text-black"><span class="icon-menu h3"></span></a></div>

          </div>

        </div>
      </div>

    </header>


