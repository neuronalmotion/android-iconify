/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android.iconify;

import static android.text.Html.fromHtml;
import static android.text.Html.toHtml;
import static com.joanzapata.android.iconify.Utils.replaceIcons;
import static com.joanzapata.android.iconify.Utils.resourceToFile;
import static java.lang.String.valueOf;

import java.io.IOException;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spanned;
import android.widget.TextView;

public final class Iconify {

    private static final String TTF_FILE = "fontawesome-webfont-4.1.0.ttf";

    public static final String TAG = Iconify.class.getSimpleName();

    private static Typeface typeface = null;
    
    private static TypefaceData customTypeface = null;

    private Iconify() {
        // Prevent instantiation
    }
    
    public static final void setCustomTypeface(Context context, 
    		Typeface typeface, 
    		String prefix, 
    		Map<String, Character> glyphs) {
    	customTypeface = new TypefaceData(typeface, prefix, glyphs);
    }
    
    public static final void addIconsCustomTypeface(TextView... textViews) {
    	if (customTypeface == null) {
    		throw new IllegalStateException("Undefined customTypeface. Did you call setCustomTypeface method?");
		}
        for (TextView textView : textViews) {
            textView.setTypeface(customTypeface.getTypeface());
            textView.setText(compute(textView.getText()));
        }
    }

    /**
     * Transform the given TextViews replacing {icon_xxx} texts with icons.
     */
    public static final void addIcons(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTypeface(getTypeface(textView.getContext()));
            textView.setText(compute(textView.getText()));
        }
    }
    
    public static CharSequence computeCustomTypeface(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            String text = toHtml((Spanned) charSequence);
            return fromHtml(Utils.replaceIconsCustomTypeface(customTypeface, new StringBuilder((text))).toString());
        }
        String text = charSequence.toString();
        return Utils.replaceIconsCustomTypeface(customTypeface, new StringBuilder(text));
    }

    public static CharSequence compute(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            String text = toHtml((Spanned) charSequence);
            return fromHtml(replaceIcons(new StringBuilder((text))).toString());
        }
        String text = charSequence.toString();
        return replaceIcons(new StringBuilder(text));
    }

    public static final void setIcon(TextView textView, IconValue value) {
        textView.setTypeface(getTypeface(textView.getContext()));
        textView.setText(valueOf(value.character));
    }

    /**
     * The typeface that contains FontAwesome icons.
     *
     * @return the typeface, or null if something goes wrong.
     */
    public static final Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromFile(resourceToFile(context, TTF_FILE));
            } catch (IOException e) {
                return null;
            }
        }
        return typeface;
    }

    public static enum IconValue {

        fa_adjust('\uf042'),
        fa_adn('\uf170'),
        fa_align_center('\uf037'),
        fa_align_justify('\uf039'),
        fa_align_left('\uf036'),
        fa_align_right('\uf038'),
        fa_ambulance('\uf0f9'),
        fa_anchor('\uf13d'),
        fa_android('\uf17b'),
        fa_angle_double_down('\uf103'),
        fa_angle_double_left('\uf100'),
        fa_angle_double_right('\uf101'),
        fa_angle_double_up('\uf102'),
        fa_angle_down('\uf107'),
        fa_angle_left('\uf104'),
        fa_angle_right('\uf105'),
        fa_angle_up('\uf106'),
        fa_apple('\uf179'),
        fa_archive('\uf187'),
        fa_arrow_circle_down('\uf0ab'),
        fa_arrow_circle_left('\uf0a8'),
        fa_arrow_circle_o_down('\uf01a'),
        fa_arrow_circle_o_left('\uf190'),
        fa_arrow_circle_o_right('\uf18e'),
        fa_arrow_circle_o_up('\uf01b'),
        fa_arrow_circle_right('\uf0a9'),
        fa_arrow_circle_up('\uf0aa'),
        fa_arrow_down('\uf063'),
        fa_arrow_left('\uf060'),
        fa_arrow_right('\uf061'),
        fa_arrow_up('\uf062'),
        fa_arrows('\uf047'),
        fa_arrows_alt('\uf0b2'),
        fa_arrows_h('\uf07e'),
        fa_arrows_v('\uf07d'),
        fa_asterisk('\uf069'),
        fa_automobile('\uf1b9'),
        fa_backward('\uf04a'),
        fa_ban('\uf05e'),
        fa_bank('\uf19c'),
        fa_bar_chart_o('\uf080'),
        fa_barcode('\uf02a'),
        fa_bars('\uf0c9'),
        fa_beer('\uf0fc'),
        fa_behance('\uf1b4'),
        fa_behance_square('\uf1b5'),
        fa_bell('\uf0f3'),
        fa_bell_o('\uf0a2'),
        fa_bitbucket('\uf171'),
        fa_bitbucket_square('\uf172'),
        fa_bitcoin('\uf15a'),
        fa_bold('\uf032'),
        fa_bolt('\uf0e7'),
        fa_bomb('\uf1e2'),
        fa_book('\uf02d'),
        fa_bookmark('\uf02e'),
        fa_bookmark_o('\uf097'),
        fa_briefcase('\uf0b1'),
        fa_btc('\uf15a'),
        fa_bug('\uf188'),
        fa_building('\uf1ad'),
        fa_building_o('\uf0f7'),
        fa_bullhorn('\uf0a1'),
        fa_bullseye('\uf140'),
        fa_cab('\uf1ba'),
        fa_calendar('\uf073'),
        fa_calendar_o('\uf133'),
        fa_camera('\uf030'),
        fa_camera_retro('\uf083'),
        fa_car('\uf1b9'),
        fa_caret_down('\uf0d7'),
        fa_caret_left('\uf0d9'),
        fa_caret_right('\uf0da'),
        fa_caret_square_o_down('\uf150'),
        fa_caret_square_o_left('\uf191'),
        fa_caret_square_o_right('\uf152'),
        fa_caret_square_o_up('\uf151'),
        fa_caret_up('\uf0d8'),
        fa_certificate('\uf0a3'),
        fa_chain('\uf0c1'),
        fa_chain_broken('\uf127'),
        fa_check('\uf00c'),
        fa_check_circle('\uf058'),
        fa_check_circle_o('\uf05d'),
        fa_check_square('\uf14a'),
        fa_check_square_o('\uf046'),
        fa_chevron_circle_down('\uf13a'),
        fa_chevron_circle_left('\uf137'),
        fa_chevron_circle_right('\uf138'),
        fa_chevron_circle_up('\uf139'),
        fa_chevron_down('\uf078'),
        fa_chevron_left('\uf053'),
        fa_chevron_right('\uf054'),
        fa_chevron_up('\uf077'),
        fa_child('\uf1ae'),
        fa_circle('\uf111'),
        fa_circle_o('\uf10c'),
        fa_circle_o_notch('\uf1ce'),
        fa_circle_thin('\uf1db'),
        fa_clipboard('\uf0ea'),
        fa_clock_o('\uf017'),
        fa_cloud('\uf0c2'),
        fa_cloud_download('\uf0ed'),
        fa_cloud_upload('\uf0ee'),
        fa_cny('\uf157'),
        fa_code('\uf121'),
        fa_code_fork('\uf126'),
        fa_codepen('\uf1cb'),
        fa_coffee('\uf0f4'),
        fa_cog('\uf013'),
        fa_cogs('\uf085'),
        fa_columns('\uf0db'),
        fa_comment('\uf075'),
        fa_comment_o('\uf0e5'),
        fa_comments('\uf086'),
        fa_comments_o('\uf0e6'),
        fa_compass('\uf14e'),
        fa_compress('\uf066'),
        fa_copy('\uf0c5'),
        fa_credit_card('\uf09d'),
        fa_crop('\uf125'),
        fa_crosshairs('\uf05b'),
        fa_css3('\uf13c'),
        fa_cube('\uf1b2'),
        fa_cubes('\uf1b3'),
        fa_cut('\uf0c4'),
        fa_cutlery('\uf0f5'),
        fa_dashboard('\uf0e4'),
        fa_database('\uf1c0'),
        fa_dedent('\uf03b'),
        fa_delicious('\uf1a5'),
        fa_desktop('\uf108'),
        fa_deviantart('\uf1bd'),
        fa_digg('\uf1a6'),
        fa_dollar('\uf155'),
        fa_dot_circle_o('\uf192'),
        fa_download('\uf019'),
        fa_dribbble('\uf17d'),
        fa_dropbox('\uf16b'),
        fa_drupal('\uf1a9'),
        fa_edit('\uf044'),
        fa_eject('\uf052'),
        fa_ellipsis_h('\uf141'),
        fa_ellipsis_v('\uf142'),
        fa_empire('\uf1d1'),
        fa_envelope('\uf0e0'),
        fa_envelope_o('\uf003'),
        fa_envelope_square('\uf199'),
        fa_eraser('\uf12d'),
        fa_eur('\uf153'),
        fa_euro('\uf153'),
        fa_exchange('\uf0ec'),
        fa_exclamation('\uf12a'),
        fa_exclamation_circle('\uf06a'),
        fa_exclamation_triangle('\uf071'),
        fa_expand('\uf065'),
        fa_external_link('\uf08e'),
        fa_external_link_square('\uf14c'),
        fa_eye('\uf06e'),
        fa_eye_slash('\uf070'),
        fa_facebook('\uf09a'),
        fa_facebook_square('\uf082'),
        fa_fast_backward('\uf049'),
        fa_fast_forward('\uf050'),
        fa_fax('\uf1ac'),
        fa_female('\uf182'),
        fa_fighter_jet('\uf0fb'),
        fa_file('\uf15b'),
        fa_file_archive_o('\uf1c6'),
        fa_file_audio_o('\uf1c7'),
        fa_file_code_o('\uf1c9'),
        fa_file_excel_o('\uf1c3'),
        fa_file_image_o('\uf1c5'),
        fa_file_movie_o('\uf1c8'),
        fa_file_o('\uf016'),
        fa_file_pdf_o('\uf1c1'),
        fa_file_photo_o('\uf1c5'),
        fa_file_picture_o('\uf1c5'),
        fa_file_powerpoint_o('\uf1c4'),
        fa_file_sound_o('\uf1c7'),
        fa_file_text('\uf15c'),
        fa_file_text_o('\uf0f6'),
        fa_file_video_o('\uf1c8'),
        fa_file_word_o('\uf1c2'),
        fa_file_zip_o('\uf1c6'),
        fa_files_o('\uf0c5'),
        fa_film('\uf008'),
        fa_filter('\uf0b0'),
        fa_fire('\uf06d'),
        fa_fire_extinguisher('\uf134'),
        fa_flag('\uf024'),
        fa_flag_checkered('\uf11e'),
        fa_flag_o('\uf11d'),
        fa_flash('\uf0e7'),
        fa_flask('\uf0c3'),
        fa_flickr('\uf16e'),
        fa_floppy_o('\uf0c7'),
        fa_folder('\uf07b'),
        fa_folder_o('\uf114'),
        fa_folder_open('\uf07c'),
        fa_folder_open_o('\uf115'),
        fa_font('\uf031'),
        fa_forward('\uf04e'),
        fa_foursquare('\uf180'),
        fa_frown_o('\uf119'),
        fa_gamepad('\uf11b'),
        fa_gavel('\uf0e3'),
        fa_gbp('\uf154'),
        fa_ge('\uf1d1'),
        fa_gear('\uf013'),
        fa_gears('\uf085'),
        fa_gift('\uf06b'),
        fa_git('\uf1d3'),
        fa_git_square('\uf1d2'),
        fa_github('\uf09b'),
        fa_github_alt('\uf113'),
        fa_github_square('\uf092'),
        fa_gittip('\uf184'),
        fa_glass('\uf000'),
        fa_globe('\uf0ac'),
        fa_google('\uf1a0'),
        fa_google_plus('\uf0d5'),
        fa_google_plus_square('\uf0d4'),
        fa_graduation_cap('\uf19d'),
        fa_group('\uf0c0'),
        fa_h_square('\uf0fd'),
        fa_hacker_news('\uf1d4'),
        fa_hand_o_down('\uf0a7'),
        fa_hand_o_left('\uf0a5'),
        fa_hand_o_right('\uf0a4'),
        fa_hand_o_up('\uf0a6'),
        fa_hdd_o('\uf0a0'),
        fa_header('\uf1dc'),
        fa_headphones('\uf025'),
        fa_heart('\uf004'),
        fa_heart_o('\uf08a'),
        fa_history('\uf1da'),
        fa_home('\uf015'),
        fa_hospital_o('\uf0f8'),
        fa_html5('\uf13b'),
        fa_image('\uf03e'),
        fa_inbox('\uf01c'),
        fa_indent('\uf03c'),
        fa_info('\uf129'),
        fa_info_circle('\uf05a'),
        fa_inr('\uf156'),
        fa_instagram('\uf16d'),
        fa_institution('\uf19c'),
        fa_italic('\uf033'),
        fa_joomla('\uf1aa'),
        fa_jpy('\uf157'),
        fa_jsfiddle('\uf1cc'),
        fa_key('\uf084'),
        fa_keyboard_o('\uf11c'),
        fa_krw('\uf159'),
        fa_language('\uf1ab'),
        fa_laptop('\uf109'),
        fa_leaf('\uf06c'),
        fa_legal('\uf0e3'),
        fa_lemon_o('\uf094'),
        fa_level_down('\uf149'),
        fa_level_up('\uf148'),
        fa_life_bouy('\uf1cd'),
        fa_life_ring('\uf1cd'),
        fa_life_saver('\uf1cd'),
        fa_lightbulb_o('\uf0eb'),
        fa_link('\uf0c1'),
        fa_linkedin('\uf0e1'),
        fa_linkedin_square('\uf08c'),
        fa_linux('\uf17c'),
        fa_list('\uf03a'),
        fa_list_alt('\uf022'),
        fa_list_ol('\uf0cb'),
        fa_list_ul('\uf0ca'),
        fa_location_arrow('\uf124'),
        fa_lock('\uf023'),
        fa_long_arrow_down('\uf175'),
        fa_long_arrow_left('\uf177'),
        fa_long_arrow_right('\uf178'),
        fa_long_arrow_up('\uf176'),
        fa_magic('\uf0d0'),
        fa_magnet('\uf076'),
        fa_mail_forward('\uf064'),
        fa_mail_reply('\uf112'),
        fa_mail_reply_all('\uf122'),
        fa_male('\uf183'),
        fa_map_marker('\uf041'),
        fa_maxcdn('\uf136'),
        fa_medkit('\uf0fa'),
        fa_meh_o('\uf11a'),
        fa_microphone('\uf130'),
        fa_microphone_slash('\uf131'),
        fa_minus('\uf068'),
        fa_minus_circle('\uf056'),
        fa_minus_square('\uf146'),
        fa_minus_square_o('\uf147'),
        fa_mobile('\uf10b'),
        fa_mobile_phone('\uf10b'),
        fa_money('\uf0d6'),
        fa_moon_o('\uf186'),
        fa_mortar_board('\uf19d'),
        fa_music('\uf001'),
        fa_navicon('\uf0c9'),
        fa_openid('\uf19b'),
        fa_outdent('\uf03b'),
        fa_pagelines('\uf18c'),
        fa_paper_plane('\uf1d8'),
        fa_paper_plane_o('\uf1d9'),
        fa_paperclip('\uf0c6'),
        fa_paragraph('\uf1dd'),
        fa_paste('\uf0ea'),
        fa_pause('\uf04c'),
        fa_paw('\uf1b0'),
        fa_pencil('\uf040'),
        fa_pencil_square('\uf14b'),
        fa_pencil_square_o('\uf044'),
        fa_phone('\uf095'),
        fa_phone_square('\uf098'),
        fa_photo('\uf03e'),
        fa_picture_o('\uf03e'),
        fa_pied_piper('\uf1a7'),
        fa_pied_piper_alt('\uf1a8'),
        fa_pied_piper_square('\uf1a7'),
        fa_pinterest('\uf0d2'),
        fa_pinterest_square('\uf0d3'),
        fa_plane('\uf072'),
        fa_play('\uf04b'),
        fa_play_circle('\uf144'),
        fa_play_circle_o('\uf01d'),
        fa_plus('\uf067'),
        fa_plus_circle('\uf055'),
        fa_plus_square('\uf0fe'),
        fa_plus_square_o('\uf196'),
        fa_power_off('\uf011'),
        fa_print('\uf02f'),
        fa_puzzle_piece('\uf12e'),
        fa_qq('\uf1d6'),
        fa_qrcode('\uf029'),
        fa_question('\uf128'),
        fa_question_circle('\uf059'),
        fa_quote_left('\uf10d'),
        fa_quote_right('\uf10e'),
        fa_ra('\uf1d0'),
        fa_random('\uf074'),
        fa_rebel('\uf1d0'),
        fa_recycle('\uf1b8'),
        fa_reddit('\uf1a1'),
        fa_reddit_square('\uf1a2'),
        fa_refresh('\uf021'),
        fa_renren('\uf18b'),
        fa_reorder('\uf0c9'),
        fa_repeat('\uf01e'),
        fa_reply('\uf112'),
        fa_reply_all('\uf122'),
        fa_retweet('\uf079'),
        fa_rmb('\uf157'),
        fa_road('\uf018'),
        fa_rocket('\uf135'),
        fa_rotate_left('\uf0e2'),
        fa_rotate_right('\uf01e'),
        fa_rouble('\uf158'),
        fa_rss('\uf09e'),
        fa_rss_square('\uf143'),
        fa_rub('\uf158'),
        fa_ruble('\uf158'),
        fa_rupee('\uf156'),
        fa_save('\uf0c7'),
        fa_scissors('\uf0c4'),
        fa_search('\uf002'),
        fa_search_minus('\uf010'),
        fa_search_plus('\uf00e'),
        fa_send('\uf1d8'),
        fa_send_o('\uf1d9'),
        fa_share('\uf064'),
        fa_share_alt('\uf1e0'),
        fa_share_alt_square('\uf1e1'),
        fa_share_square('\uf14d'),
        fa_share_square_o('\uf045'),
        fa_shield('\uf132'),
        fa_shopping_cart('\uf07a'),
        fa_sign_in('\uf090'),
        fa_sign_out('\uf08b'),
        fa_signal('\uf012'),
        fa_sitemap('\uf0e8'),
        fa_skype('\uf17e'),
        fa_slack('\uf198'),
        fa_sliders('\uf1de'),
        fa_smile_o('\uf118'),
        fa_sort('\uf0dc'),
        fa_sort_alpha_asc('\uf15d'),
        fa_sort_alpha_desc('\uf15e'),
        fa_sort_amount_asc('\uf160'),
        fa_sort_amount_desc('\uf161'),
        fa_sort_asc('\uf0de'),
        fa_sort_desc('\uf0dd'),
        fa_sort_down('\uf0dd'),
        fa_sort_numeric_asc('\uf162'),
        fa_sort_numeric_desc('\uf163'),
        fa_sort_up('\uf0de'),
        fa_soundcloud('\uf1be'),
        fa_space_shuttle('\uf197'),
        fa_spinner('\uf110'),
        fa_spoon('\uf1b1'),
        fa_spotify('\uf1bc'),
        fa_square('\uf0c8'),
        fa_square_o('\uf096'),
        fa_stack_exchange('\uf18d'),
        fa_stack_overflow('\uf16c'),
        fa_star('\uf005'),
        fa_star_half('\uf089'),
        fa_star_half_empty('\uf123'),
        fa_star_half_full('\uf123'),
        fa_star_half_o('\uf123'),
        fa_star_o('\uf006'),
        fa_steam('\uf1b6'),
        fa_steam_square('\uf1b7'),
        fa_step_backward('\uf048'),
        fa_step_forward('\uf051'),
        fa_stethoscope('\uf0f1'),
        fa_stop('\uf04d'),
        fa_strikethrough('\uf0cc'),
        fa_stumbleupon('\uf1a4'),
        fa_stumbleupon_circle('\uf1a3'),
        fa_subscript('\uf12c'),
        fa_suitcase('\uf0f2'),
        fa_sun_o('\uf185'),
        fa_superscript('\uf12b'),
        fa_support('\uf1cd'),
        fa_table('\uf0ce'),
        fa_tablet('\uf10a'),
        fa_tachometer('\uf0e4'),
        fa_tag('\uf02b'),
        fa_tags('\uf02c'),
        fa_tasks('\uf0ae'),
        fa_taxi('\uf1ba'),
        fa_tencent_weibo('\uf1d5'),
        fa_terminal('\uf120'),
        fa_text_height('\uf034'),
        fa_text_width('\uf035'),
        fa_th('\uf00a'),
        fa_th_large('\uf009'),
        fa_th_list('\uf00b'),
        fa_thumb_tack('\uf08d'),
        fa_thumbs_down('\uf165'),
        fa_thumbs_o_down('\uf088'),
        fa_thumbs_o_up('\uf087'),
        fa_thumbs_up('\uf164'),
        fa_ticket('\uf145'),
        fa_times('\uf00d'),
        fa_times_circle('\uf057'),
        fa_times_circle_o('\uf05c'),
        fa_tint('\uf043'),
        fa_toggle_down('\uf150'),
        fa_toggle_left('\uf191'),
        fa_toggle_right('\uf152'),
        fa_toggle_up('\uf151'),
        fa_trash_o('\uf014'),
        fa_tree('\uf1bb'),
        fa_trello('\uf181'),
        fa_trophy('\uf091'),
        fa_truck('\uf0d1'),
        fa_try('\uf195'),
        fa_tumblr('\uf173'),
        fa_tumblr_square('\uf174'),
        fa_turkish_lira('\uf195'),
        fa_twitter('\uf099'),
        fa_twitter_square('\uf081'),
        fa_umbrella('\uf0e9'),
        fa_underline('\uf0cd'),
        fa_undo('\uf0e2'),
        fa_university('\uf19c'),
        fa_unlink('\uf127'),
        fa_unlock('\uf09c'),
        fa_unlock_alt('\uf13e'),
        fa_unsorted('\uf0dc'),
        fa_upload('\uf093'),
        fa_usd('\uf155'),
        fa_user('\uf007'),
        fa_user_md('\uf0f0'),
        fa_users('\uf0c0'),
        fa_video_camera('\uf03d'),
        fa_vimeo_square('\uf194'),
        fa_vine('\uf1ca'),
        fa_vk('\uf189'),
        fa_volume_down('\uf027'),
        fa_volume_off('\uf026'),
        fa_volume_up('\uf028'),
        fa_warning('\uf071'),
        fa_wechat('\uf1d7'),
        fa_weibo('\uf18a'),
        fa_weixin('\uf1d7'),
        fa_wheelchair('\uf193'),
        fa_windows('\uf17a'),
        fa_won('\uf159'),
        fa_wordpress('\uf19a'),
        fa_wrench('\uf0ad'),
        fa_xing('\uf168'),
        fa_xing_square('\uf169'),
        fa_yahoo('\uf19e'),
        fa_yen('\uf157'),
        fa_youtube('\uf167'),
        fa_youtube_play('\uf16a'),
        fa_youtube_square('\uf166');

        char character;

        IconValue(char character) {
            this.character = character;
        }

        public String formattedName() {
            return "{" + name() + "}";
        }

        public char character() {
            return character;
        }
    }

	public static TypefaceData getCustomTypefaceData() {
		return customTypeface;
	}
}
