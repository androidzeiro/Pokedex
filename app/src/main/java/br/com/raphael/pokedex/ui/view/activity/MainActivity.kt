package br.com.raphael.pokedex.ui.view.activity

import br.com.raphael.pokedex.databinding.ActivityMainBinding
import br.com.raphael.pokedex.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)