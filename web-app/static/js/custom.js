/*-----------------------------------------------------------------------------------
/* Custom JS
-----------------------------------------------------------------------------------*/
	  
/* ----------------- Start Document ----------------- */
jQuery(document).ready(function() {

/*----------------------------------------------------*/
/*	Main Navigation
/*----------------------------------------------------*/

	/* Menu */
	(function() {

		var $mainNav    = $('#navigation').children('ul');

		$mainNav.on('mouseenter', 'li', function() {
			var $this    = $(this),
				$subMenu = $this.children('ul');
			if( $subMenu.length ) $this.addClass('hover');
			$subMenu.hide().stop(true, true).slideDown('fast');
		}).on('mouseleave', 'li', function() {
			$(this).removeClass('hover').children('ul').stop(true, true).slideUp('fast');
		});
		
	})();
	
	/* Responsive Menu */
	(function() {
		selectnav('nav', {
			label: 'Menu',
			nested: true,
			indent: '-'
		});
				
	})();


/*----------------------------------------------------*/
/*	Image Overlay
/*----------------------------------------------------*/

	$(document).ready(function () {
	  $('.picture a').hover(function () {
		$(this).find('.image-overlay-zoom, .image-overlay-link').stop().fadeTo('fast', 1);
	  },function () {
		$(this).find('.image-overlay-zoom, .image-overlay-link').stop().fadeTo('fast', 0);
	  });
	});


/*----------------------------------------------------*/
/*	Back To Top Button
/*----------------------------------------------------*/

	jQuery('#scroll-top-top a').click(function(){
		jQuery('html, body').animate({scrollTop:0}, 300); 
		return false; 
	}); 
	
	
/*----------------------------------------------------*/
/*	Accordion
/*----------------------------------------------------*/
	(function() {

		var $container = $('.acc-container'),
			$trigger   = $('.acc-trigger');

		$container.hide();
		$trigger.first().addClass('active').next().show();

		var fullWidth = $container.outerWidth(true);
		$trigger.css('width', fullWidth);
		$container.css('width', fullWidth);
		
		$trigger.on('click', function(e) {
			if( $(this).next().is(':hidden') ) {
				$trigger.removeClass('active').next().slideUp(300);
				$(this).toggleClass('active').next().slideDown(300);
			}
			e.preventDefault();
		});

		// Resize
		$(window).on('resize', function() {
			fullWidth = $container.outerWidth(true)
			$trigger.css('width', $trigger.parent().width() );
			$container.css('width', $container.parent().width() );
		});

	})();
	
/*----------------------------------------------------*/
/*	Alert Boxes
/*----------------------------------------------------*/
jQuery(document).ready(function()
{
	jQuery(document.body).pixusNotifications({
			speed: 300,
			animation: 'fadeAndSlide',
			hideBoxes: false
	});
});

(function()
{
	$.fn.pixusNotifications = function(options)
	{
		var defaults = {
			speed: 200,
			animation: 'fade',
			hideBoxes: false
		};
		
		var options = $.extend({}, defaults, options);
		
		return this.each(function()
		{
			var wrapper = $(this),
				notification = wrapper.find('.notification'),
				content = notification.find('p'),
				title = content.find('strong'),
				closeBtn = $('<a class="close" href="#"></a>');
			
			$(document.body).find('.notification').each(function(i)
			{
				var i = i+1;
				$(this).attr('id', 'notification_'+i);
			});
			
			notification.filter('.closeable').append(closeBtn);
			
			closeButton = notification.find('> .close');
			
			closeButton.click(function()
			{
				hideIt( $(this).parent() );
				return false;
			});			
			
			function hideIt(object)
			{
				switch(options.animation)
				{
					case 'fade': fadeIt(object);     break;
					case 'slide': slideIt(object);     break;
					case 'box': boxAnimIt(object);     break;
					case 'fadeAndSlide': fadeItSlideIt(object);     break;
					default: fadeItSlideIt(object);
				}
			};
			
			function fadeIt(object)
			{	object
				.fadeOut(options.speed);
			}			
			function slideIt(object)
			{	object
				.slideUp(options.speed);
			}			
			function fadeItSlideIt(object)
			{	object
				.fadeTo(options.speed, 0, function() { slideIt(object) } );
			}			
			function boxAnimIt(object)
			{	object
				.hide(options.speed);
			}
			
			if (options.hideBoxes){}
			
			else if (! options.hideBoxes)
			{
				notification.css({'display': 'block', 'visiblity': 'visible'});
			}
			
		});
	};
})();

/*----------------------------------------------------*/
/*	Tabs
/*----------------------------------------------------*/

	(function() {

		var $tabsNav    = $('.tabs-nav'),
			$tabsNavLis = $tabsNav.children('li'),
			$tabContent = $('.tab-content');

		$tabsNav.each(function() {
			var $this = $(this);

			$this.next().children('.tab-content').stop(true,true).hide()
												 .first().show();

			$this.children('li').first().addClass('active').stop(true,true).show();
		});

		$tabsNavLis.on('click', function(e) {
			var $this = $(this);

			$this.siblings().removeClass('active').end()
				 .addClass('active');
			
			$this.parent().next().children('.tab-content').stop(true,true).hide()
														  .siblings( $this.find('a').attr('href') ).fadeIn();

			e.preventDefault();
		});

	})();

	
/*----------------------------------------------------*/
/*	Contact Form
/*----------------------------------------------------*/
(function() {
var emailReg = /^[a-zA-Z0-9._-]+@([a-zA-Z0-9.-]+\.)+[a-zA-Z0-9.-]{2,4}$/;

	// Validating
	
	function validateSubject(subject) {
		if (subject.val()=='') {subject.addClass('validation-error'); return false;}
		else {subject.removeClass('validation-error'); return true;}
	}
	
	function validateName(name) {
		if (name.val()=='') {name.addClass('validation-error'); return false;}
		else {name.removeClass('validation-error'); return true;}
	}

	function validateEmail(email,regex) {
		if (!regex.test(email.val())) {email.addClass('validation-error'); return false;}
		else {email.removeClass('validation-error'); return true;}
	}
		
	function validateMessage(message) {
		if (message.val()=='') {message.addClass('validation-error'); return false;}
		else {message.removeClass('validation-error'); return true;}
	}
				
	$('#send').click(function() {
	
		var result=true;
		
		var subject = $('input[name=subject]');
		var name = $('input[name=name]');
		var email = $('input[name=email]');
		var message = $('textarea[name=message]');
				
		// Validate
		if(!validateName(subject)) result=false;
		if(!validateName(name)) result=false;
		if(!validateEmail(email,emailReg)) result=false;
		if(!validateMessage(message)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = 'subject=' + subject.val() + '&name=' + name.val() + '&email=' + email.val() + '&message='  + encodeURIComponent(message.val());
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	

					// Loading icon
					$('.loading').fadeOut('slow');	
						
					//show the success message
					$('.success-message').slideDown('slow');
											
					// Disable send button
					$('#send').attr('disabled',true);
					
				}
				
				else {
					$('.loading').fadeOut('slow')
					alert('Sorry, unexpected error. Please try again later.');				
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=subject]').blur(function(){validateSubject($(this));});
	$('input[name=name]').blur(function(){validateName($(this));});
	$('input[name=email]').blur(function(){validateEmail($(this),emailReg); });
	$('textarea[name=message]').blur(function(){validateMessage($(this)); });
	   
})();

/*----------------------------------------------------*/
/*	Register Form
/*----------------------------------------------------*/
(function() {
var emailReg = /^[a-zA-Z0-9._-]+@([a-zA-Z0-9.-]+\.)+[a-zA-Z0-9.-]{2,4}$/;

	// Validating
	function validateName(name) {
		if (name.val()=='') {name.addClass('validation-error'); return false;}
		else if (name.val().indexOf(' ')>=0){name.addClass('validation-error'); return false;}
		else {name.removeClass('validation-error'); return true;}
	}

	function validatePswd(pswd) {
		if (pswd.val()=='') {pswd.addClass('validation-error'); return false;}
		else {pswd.removeClass('validation-error'); return true;}
	}

	function validatePswdConfirm(pswdConfirm,pswd) {
		if (pswdConfirm.val()!=pswd.val()) {pswdConfirm.addClass('validation-error'); return false;}
		else {pswdConfirm.removeClass('validation-error'); return true;}
	}

	function validateEmail(email,regex) {
		if (!regex.test(email.val())) {email.addClass('validation-error'); return false;}
		else {email.removeClass('validation-error'); return true;}
	}
				
	$('#registerBtn').click(function() {
	
		var result=true;
		
		var name = $('input[name=name]');
		var pswd = $('input[name=pswd]');
		var pswdConfirm = $('input[name=pswdConfirm]');
		var email = $('input[name=email]');
				
		// Validate
		if(!validateName(name)) result=false;
		if(!validatePswd(pswd)) result=false;
		if(!validatePswdConfirm(pswdConfirm,pswd)) result=false;
		if(!validateEmail(email,emailReg)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = '&name=' + name.val() + '&pswd=' + pswd.val() + '&email=' + email.val();
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	

					// Loading icon
					$('.loading').fadeOut('slow');	
											
					// Disable register button
					$('#registerBtn').attr('disabled',true);
					
					location.href=$contextPath+"/";
					
				}else{
					$('.loading').fadeOut('slow')				
					//show the success message
					$('.success-message').slideDown('slow');
					// Disable fields
					$('.text').attr('disabled',false);
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=name]').blur(function(){validateName($(this));});
	$('input[name=pswd]').blur(function(){validatePswd($(this));});
	$('input[name=pswdConfirm]').blur(function(){validatePswdConfirm($(this),$('input[name=pswd]'));});
	$('input[name=email]').blur(function(){validateEmail($(this),emailReg); });
	   
})();

/*----------------------------------------------------*/
/*	Sign In Form
/*----------------------------------------------------*/
(function() {
	// Validating
	function validateName(name) {
		if (name.val()=='') {name.addClass('validation-error'); return false;}
		else {name.removeClass('validation-error'); return true;}
	}

	function validatePswd(pswd) {
		if (pswd.val()=='') {pswd.addClass('validation-error'); return false;}
		else {pswd.removeClass('validation-error'); return true;}
	}
				
	$('#signInBtn').click(function() {
	
		var result=true;
		
		var name = $('input[name=username]');
		var pswd = $('input[name=password]');
				
		// Validate
		if(!validateName(name)) result=false;
		if(!validatePswd(pswd)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = 'username=' + name.val() + '&password=' + pswd.val();
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	
					// Loading icon
					$('.loading').fadeOut('slow');	
											
					// Disable register button
					$('#signInBtn').attr('disabled',true);
					
					location.href=$contextPath+"/";
				}else {
					$('.loading').fadeOut('slow')
					$('.success-message').slideDown('slow');
					$('#signInBtn').attr('disabled',false);
					// Disable fields
					$('.text').attr('disabled',false);
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=name]').blur(function(){validateName($(this));});
	$('input[name=pswd]').blur(function(){validatePswd($(this));});
	   
})();

/*----------------------------------------------------*/
/*	Profile Form
/*----------------------------------------------------*/
(function() {
var emailReg = /^[a-zA-Z0-9._-]+@([a-zA-Z0-9.-]+\.)+[a-zA-Z0-9.-]{2,4}$/;

	function validateEmail(email,regex) {
		if (!regex.test(email.val())) {email.addClass('validation-error'); return false;}
		else {email.removeClass('validation-error'); return true;}
	}
	
	function validateIntro(intro) {
		if (intro.val()=='') {intro.addClass('validation-error'); return false;}
		else {intro.removeClass('validation-error'); return true;}
	}
				
	$('#profileBtn').click(function() {
	
		var result=true;
		
		var email = $('input[name=email]');
		var intro = $('textarea[name=intro]');
				
		// Validate
		if(!validateEmail(email,emailReg)) result=false;
		if(!validateIntro(intro)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = 'email=' + email.val() + '&intro=' + intro.val();
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	

					// Loading icon
					$('.loading').fadeOut('slow');	
											
					// Disable register button
					$('#registerBtn').attr('disabled',true);
					
					location.href=$contextPath+"/designer/"+designerId;
					
				}else {
					$('.loading').fadeOut('slow')
						
					//show the success message
					$('.success-message').slideDown('slow');
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=email]').blur(function(){validateEmail($(this),emailReg); });
	$('textarea[name=intro]').blur(function(){validateIntro($(this));});
})();

/*----------------------------------------------------*/
/*	Change Password Form
/*----------------------------------------------------*/
(function() {
	function validateCurrentPswd(currentPswd) {
		if (currentPswd.val()=='') {currentPswd.addClass('validation-error'); return false;}
		else {currentPswd.removeClass('validation-error'); return true;}
	}

	function validateNewPswd(newPswd) {
		if (newPswd.val()=='') {newPswd.addClass('validation-error'); return false;}
		else {newPswd.removeClass('validation-error'); return true;}
	}

	function validateNewPswdConfirm(newPswd,newPswdConfirm) {
		if (newPswdConfirm.val()=='') {newPswdConfirm.addClass('validation-error'); return false;}
		else if(newPswd.val()!=newPswdConfirm.val()){newPswdConfirm.addClass('validation-error'); return false;}
		else {newPswdConfirm.removeClass('validation-error'); return true;}
	}
				
	$('#pswdBtn').click(function() {
	
		var result=true;
		
		var currentPswd = $('input[name=currentPswd]');
		var newPswd = $('input[name=newPswd]');
		var newPswdConfirm = $('input[name=newPswdConfirm]');
				
		// Validate
		if(!validateCurrentPswd(currentPswd)) result=false;
		if(!validateNewPswd(newPswd)) result=false;
		if(!validateNewPswdConfirm(newPswd,newPswdConfirm)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = 'currentPswd=' + currentPswd.val() + '&newPswd=' + newPswd.val();
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	

					// Loading icon
					$('.loading').fadeOut('slow');	
											
					// Disable register button
					$('#pswdBtn').attr('disabled',true);
					
					location.href=$contextPath+"/designer/"+designerId;
					
				}else {
					$('.loading').fadeOut('slow')
						
					//show the success message
					$('.success-message').slideDown('slow');		
					$('#pswdBtn').attr('disabled',false);
					// Disable fields
					$('.text').attr('disabled',false);
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=currentPswd]').blur(function(){validateCurrentPswd($(this));});
	$('input[name=newPswd]').blur(function(){validateNewPswd($(this));});
	$('input[name=newPswdConfirm]').blur(function(){validateNewPswdConfirm($('input[name=newPswd]'),$(this));});
})();

/*----------------------------------------------------*/
/*	Profile Form
/*----------------------------------------------------*/
(function() {
	function validateTitle(title) {
		if (title.val()=='') {title.addClass('validation-error'); return false;}
		else {title.removeClass('validation-error'); return true;}
	}
	function validateContent(content) {
		if (content.val()=='') {content.addClass('validation-error'); return false;}
		else {content.removeClass('validation-error'); return true;}
	}
				
	$('#blogBtn').click(function() {
	
		var result=true;
		
		var title = $('input[name=title]');
		var content = $('textarea[name=content]');
				
		// Validate
		if(!validateTitle(title)) result=false;
		if(!validateContent(content)) result=false;
		
		if(result==false) return false;
				
		// Data
		var data = 'title=' + title.val() + '&content=' + content.val()+'&pureText='+editor.getContentTxt();
		
		// Disable fields
		$('.text').attr('disabled','true');
		
		// Loading icon
		$('.loading').show();
		
		// Start jQuery
		$.ajax({
		
			// PHP file that processes the data and send mail
			url: $formAction,	
			
			// POST method is used
			type: "POST",

			// Pass the data			
			data: data,		
			
			//Do not cache the page
			cache: false,
			
			// Success
			success: function (html) {				
			
				if (html==1) {	

					// Loading icon
					$('.loading').fadeOut('slow');	
											
					// Disable register button
					$('#blogBtn').attr('disabled',true);
					
					location.href=$contextPath+"/designer/"+designerId+"/blogs";
					
				}else {
					$('.loading').fadeOut('slow')
						
					//show the success message
					$('.success-message').slideDown('slow');
				}
			}		
		});
	
		return false;
		
	});
		
	$('input[name=title]').blur(function(){validateTitle($(this)); });
	$('textarea[name=content]').blur(function(){validateContent($(this));});
})();
/*----------------------------------------------------*/
/*	Isotope Portfolio Filter
/*----------------------------------------------------*/

	$(function() {
		var $container = $('#portfolio-wrapper');
				$select = $('#filters select');
				
		// initialize Isotope
		$container.isotope({
		  // options...
		  resizable: false, // disable normal resizing
		  // set columnWidth to a percentage of container width
		  masonry: { columnWidth: $container.width() / 12 }
		});

		// update columnWidth on window resize
		$(window).smartresize(function(){
		  $container.isotope({
			// update columnWidth to a percentage of container width
			masonry: { columnWidth: $container.width() / 12 }
		  });
		});
		
		
	  $container.isotope({
		itemSelector : '.portfolio-item'
	  });
	  
	$select.change(function() {
			var filters = $(this).val();
	
			$container.isotope({
				filter: filters
			});
		});
	  
	  var $optionSets = $('#filters .option-set'),
		  $optionLinks = $optionSets.find('a');

	  $optionLinks.click(function(){
		var $this = $(this);
		// don't proceed if already selected
		if ( $this.hasClass('selected') ) {
		  return false;
		}
		var $optionSet = $this.parents('.option-set');
		$optionSet.find('.selected').removeClass('selected');
		$this.addClass('selected');
  
		// make option object dynamically, i.e. { filter: '.my-filter-class' }
		var options = {},
			key = $optionSet.attr('data-option-key'),
			value = $this.attr('data-option-value');
		// parse 'false' as false boolean
		value = value === 'false' ? false : value;
		options[ key ] = value;
		if ( key === 'layoutMode' && typeof changeLayoutMode === 'function' ) {
		  // changes in layout modes need extra logic
		  changeLayoutMode( $this, options )
		} else {
		  // otherwise, apply new options
		  $container.isotope( options );
		}
		
		return false;
	  });
});
	
/*----------------------------------------------------*/
/*	Fancybox
/*----------------------------------------------------*/
(function() {

	$('[rel=image]').fancybox({
		type        : 'image',
		openEffect  : 'fade',
		closeEffect	: 'fade',
		nextEffect  : 'fade',
		prevEffect  : 'fade',
		helpers     : {
			title   : {
				type : 'inside'
			}
		}
	});
	
	$('[rel=image-gallery]').fancybox({
		nextEffect  : 'fade',
		prevEffect  : 'fade',
		helpers     : {
			title   : {
				type : 'inside'
			},
			buttons  : {},
			media    : {}
		}
	});
	
	
})();
	
/* ------------------ End Document ------------------ */
});
