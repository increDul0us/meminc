<#include "header.ftl">
  <div class="container-fluid" data-aos="fade" data-aos-delay="500">
    <div class="swiper-container images-carousel">
        <div class="swiper-wrapper">
            <#list memes>
                <#items as meme>
                    <div class="swiper-slide">
                        <div class="image-wrap">
                            <div class="image-info">
                            <h2 class="mb-3">${meme.name}</h2>
                            <a href="/create/${meme.id}" class="btn btn-outline-white py-2 px-4">Meminc This</a>
                            </div>
                            <img src="${meme.url}" alt="Image">
                        </div>
                    </div>
                </#items>
            </#list>
        </div>

        <div class="swiper-pagination"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
        <div class="swiper-scrollbar"></div>
    </div>
  </div>

<#include "footer.ftl">
