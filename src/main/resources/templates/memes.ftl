<#include "header.ftl">


<div class="site-section"  data-aos="fade">
    <div class="container-fluid">

        <div class="row justify-content-center">

            <div class="col-md-7">
            <div class="row mb-5">
                <div class="col-12 ">
                <h2 class="site-section-heading text-center">Memeinc Gallery</h2>
                </div>
            </div>
            </div>

        </div>
        <div class="row" id="lightgallery">
            <#list memes>
                <#items as meme>
                    <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2 item" data-aos="fade" data-src="${meme.url}" data-sub-html="<h4>${meme.name}</h4>">
                    <a href="#"><img src="${meme.url}" alt="IMage" class="img-fluid"></a>
                    </div>
                </#items>
            </#list>
        </div>
    </div>
</div>

<#include "footer.ftl">
