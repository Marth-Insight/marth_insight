<?php

$url = urldecode($_GET['url']);
echo file_get_contents($url);